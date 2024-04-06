package com.example.ImperiaConquest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.ImperiaConquest.Empire.*;
import com.example.ImperiaConquest.Mine.*;
import jakarta.validation.constraints.Min;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class MineServiceTest {

    @Mock
    EmpireService empireService;

    @Mock
    MineRepository mineRepository;

    @InjectMocks
    MineService mineService;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSubmitUpgradeMineWhenCanUpgrade() {
        Mine mine = new Mine();
        Empire empire = new Empire();
        empire.setGold(50);
        String resource = "gold";
        String result = mineService.submitUpgradeMine(mine, empire, resource, model);
        assertEquals("redirect:/empire/show", result);
    }

    @Test
    void testSubmitUpgradeMineWhenCannotUpgrade() {
        Mine mine = new Mine();
        Empire empire = mock(Empire.class);
        empire.setGold(20);
        String resource = "gold";
        String result = mineService.submitUpgradeMine(mine, empire, resource, model);
        assertEquals("empire/show", result);
        verify(model, times(3)).addAttribute(anyString(), any());
    }

    @Test
    void testPayUpgradeWithGold() {
        Empire empire = mock(Empire.class);
        empire.setGold(50);
        mineService.payUpgrade(empire, "gold");
        verify(empire).getGold();
        verify(empire, never()).getIron();
        verify(empire, never()).getWood();
        verify(empireService).updateEmpire(any());
        assertEquals(0, empire.getGold());
    }

    @Test
    void testPayUpgradeWithIron() {
        Empire empire = mock(Empire.class);
        empire.setGold(100);
        mineService.payUpgrade(empire, "iron");
        verify(empire).getIron();
        verify(empire, never()).getGold();
        verify(empire, never()).getWood();
        verify(empireService).updateEmpire(any());
        assertEquals(0, empire.getIron());
    }

    @Test
    void testPayUpgradeWithWood() {
        Empire empire = mock(Empire.class);
        empire.setGold(200);
        mineService.payUpgrade(empire, "wood");
        verify(empire).getWood();
        verify(empire, never()).getGold();
        verify(empire, never()).getIron();
        verify(empireService).updateEmpire(any());
        assertEquals(0, empire.getWood());
    }

    @Test
    void testMiningWithGold() {
        Empire empire = mock(Empire.class);
        Mine mine = mock(Mine.class);
        String resource = "gold";
        mineService.mining(empire, mine, resource);
        verify(empire).setGold(anyInt());
        verify(mine).setLastMining(any());
        verify(empireService).updateEmpire(any());
    }

    @Test
    void testMiningWithIron() {
        Empire empire = mock(Empire.class);
        Mine mine = mock(Mine.class);
        String resource = "iron";
        mineService.mining(empire, mine, resource);
        verify(empire).setIron(anyInt());
        verify(mine).setLastMining(any());
        verify(empireService).updateEmpire(any());
    }

    @Test
    void testMiningWithWood() {
        Empire empire = mock(Empire.class);
        Mine mine = mock(Mine.class);
        String resource = "wood";
        mineService.mining(empire, mine, resource);
        verify(empire).setWood(anyInt());
        verify(mine).setLastMining(any());
        verify(empireService).updateEmpire(any());
    }

    @Test
    void testSubmitMiningWhenCanMine() {
        Empire empire = mock(Empire.class);
        Mine mine = mock(Mine.class);
        String resource = "gold";
        String result = mineService.submitMining(empire, mine, resource, model);
        assertEquals("redirect:/empire/show", result);
        verify(model, never()).addAttribute(anyString(), any());
    }

    @Test
    void testSubmitMiningWhenCannotMine() {
        Empire empire = new Empire();
        Mine mine = new Mine();
        mine.setLastMining(LocalDateTime.now());
        System.out.println(mine.getLastMining());
        String resource = "gold";
        String result = mineService.submitMining(empire, mine, resource, model);
        assertEquals("empire/show", result);
        verify(model, times(3)).addAttribute(anyString(), any());
    }

    @Test
    void testSetUpMine() {
        Mine mine = mineService.setUpMine(mock(Mine.class));
        verify(mine).setGoldMiningCapacity(anyInt());
        verify(mine).setIronMiningCapacity(anyInt());
        verify(mine).setWoodMiningCapacity(anyInt());
        verify(mine).setName(anyString());
    }

    @Test
    void testGetMinutesToMineWhenLastMineBefore60Minutes() {
        Mine mine = new Mine();
        mine.setLastMining(LocalDateTime.now().minusMinutes(60));
        long result = mineService.getMinutesToMine(mine);
        assertEquals(0, result);
    }

    @Test
    void testGetMinutesToMineWhenLastMineBefore10Minutes() {
        Mine mine = new Mine();
        mine.setLastMining(LocalDateTime.now().minusMinutes(10));
        long result = mineService.getMinutesToMine(mine);
        assertEquals(50, result);
    }


    @Test
    void testGetMinutesToMineWhenLastMineIsNow() {
        Mine mine = new Mine();
        mine.setLastMining(LocalDateTime.now());
        long result = mineService.getMinutesToMine(mine);
        assertEquals(60, result);
    }

    @Test
    void testCheckIfCanMineWhenLastMiningIsNull() {
        Mine mine = new Mine();
        boolean result = mineService.checkIfCanMine(mine);
        assertEquals(true, result);
    }

    @Test
    void testCheckIfCanMineWhenCanMineAndLastMiningIsNotNull() {
        Mine mine = new Mine();
        mine.setLastMining(LocalDateTime.now().minusHours(1));
        boolean result = mineService.checkIfCanMine(mine);
        assertEquals(true, result);
    }

    @Test
    void testCheckIfCanMineWhenCantMineAndLastMiningIsNotNull() {
        Mine mine = new Mine();
        mine.setLastMining(LocalDateTime.now());
        boolean result = mineService.checkIfCanMine(mine);
        assertEquals(false, result);
    }

    @Test
    void testCheckIfCanPayUpgradeWithGoldWhenCanPay() {
        Empire empire = new Empire();
        empire.setGold(50);
        boolean result = mineService.checkIfCanPayUpgrade(empire, "gold");
        assertEquals(true, result);
    }

    @Test
    void testCheckIfCanPayUpgradeWithGoldWhenCantPay() {
        Empire empire = new Empire();
        empire.setGold(20);
        boolean result = mineService.checkIfCanPayUpgrade(empire, "gold");
        assertEquals(false, result);
    }

    @Test
    void testCheckIfCanPayUpgradeWithIronWhenCanPay() {
        Empire empire = new Empire();
        empire.setIron(100);
        boolean result = mineService.checkIfCanPayUpgrade(empire, "iron");
        assertEquals(true, result);
    }

    @Test
    void testCheckIfCanPayUpgradeWithIronWhenCantPay() {
        Empire empire = new Empire();
        empire.setIron(20);
        boolean result = mineService.checkIfCanPayUpgrade(empire, "iron");
        assertEquals(false, result);
    }

    @Test
    void testCheckIfCanPayUpgradeWithWoodWhenCanPay() {
        Empire empire = new Empire();
        empire.setWood(200);
        boolean result = mineService.checkIfCanPayUpgrade(empire, "wood");
        assertEquals(true, result);
    }

    @Test
    void testCheckIfCanPayUpgradeWithWoodWhenCantPay() {
        Empire empire = new Empire();
        empire.setWood(20);
        boolean result = mineService.checkIfCanPayUpgrade(empire, "wood");
        assertEquals(false, result);
    }

    @Test
    void testCalculateHoursDifferenceWhenLastMiningBefore1Hour() {
        long result = mineService.calculateHoursDifference(LocalDateTime.now().minusHours(1));
        assertEquals(1, result);
    }

    @Test
    void testCalculateHoursDifferenceWhenLastMiningIsNow() {
        long result = mineService.calculateHoursDifference(LocalDateTime.now());
        assertEquals(0, result);
    }

    @Test
    void testUpgradeMine() {
        Mine mine = mock(Mine.class);
        mineService.upgradeMine(mine);
        verify(mine).setGoldMiningCapacity(anyInt());
        verify(mine).setIronMiningCapacity(anyInt());
        verify(mine).setWoodMiningCapacity(anyInt());
    }

    @Test
    void testUpdateMine() {
        Mine mine = mock(Mine.class);
        mineService.updateMine(mine);
        verify(mineRepository).save(any());
    }
}