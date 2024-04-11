package com.example.ImperiaConquest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.ImperiaConquest.Building.BuildingService;
import com.example.ImperiaConquest.Empire.*;
import com.example.ImperiaConquest.Mine.*;
import com.example.ImperiaConquest.User.MyUserDetails;
import com.example.ImperiaConquest.User.User;
import com.example.ImperiaConquest.User.UserRepository;
import jakarta.validation.constraints.Min;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@ExtendWith(MockitoExtension.class)
public class EmpireServiceTests {
    @InjectMocks
    EmpireService empireService;

    @Mock
    MineRepository mineRepository;

    @Mock
    MineService mineService;

    @Mock
    UserRepository userRepository;

    @Mock
    EmpireRepository empireRepository;

    @Mock
    BuildingService buildingService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetGarrisonBuildings() {
        Empire empire = mock(Empire.class);
        empireService.getGarrisonBuildings(empire);
        verify(buildingService).getGarrisonBuildingsByEmpire(empire);
    }

    @Test
    void testGetQuartersBuildings() {
        Empire empire = mock(Empire.class);
        empireService.getQuartersBuildings(empire);
        verify(buildingService).getQuartersBuildingsByEmpire(empire);
    }

    @Test
    void testGetBarracksBuildings() {
        Empire empire = mock(Empire.class);
        empireService.getBarracksBuildings(empire);
        verify(buildingService).getBarracksBuildingsByEmpire(empire);
    }

    @Test
    void submitSaveEmpireWhenNoErrors() {
        Empire empire = mock(Empire.class);
        MyUserDetails myuserDetails = mock(MyUserDetails.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        String result = empireService.submitSaveEmpire(empire, bindingResult, myuserDetails);
        assertEquals("redirect:/empire/add", result);
    }

    @Test
    void submitSaveEmpireWhenErrors() {
        Empire empire = mock(Empire.class);
        MyUserDetails myuserDetails = mock(MyUserDetails.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        String result = empireService.submitSaveEmpire(empire, bindingResult, myuserDetails);
        assertEquals("empire/add", result);
    }

    @Test
    void testSetResources() {
        Empire empire = mock(Empire.class);
        empireService.setResources(empire);
        verify(empire).setGold(anyInt());
        verify(empire).setIron(anyInt());
        verify(empire).setWood(anyInt());
    }

    @Test
    void testGetEmpireByUsername() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        Empire empire = new Empire();
        empire.setId(1L);
        when(userRepository.getUserByUsername("testUser")).thenReturn(user);
        when(empireRepository.getEmpireByUserId(1L)).thenReturn(empire);
        Empire result = empireService.getEmpireByUsername("testUser");
        assertEquals(empire, result);
    }

    @Test
    void testBuyMine() {
        Empire empire = mock(Empire.class);
        Mine mine = mock(Mine.class);
        String resource = "gold";
        empireService.buyMine(mine, empire, resource);
        verify(empire).addMine(mine);
    }

    @Test
    void testHasEnoughResourcesForPurchaseWhenCanPurchase() {
        Empire empire = new Empire();
        empire.setGold(50);
        empire.setIron(100);
        empire.setWood(200);
        boolean result = empireService.hasEnoughResourcesForPurchase(empire, 50, 100, 200);
        assertEquals(true, result);
    }

    @Test
    void testHasEnoughResourcesForPurchaseWhenCannotPurchase() {
        Empire empire = new Empire();
        boolean result = empireService.hasEnoughResourcesForPurchase(empire, 50, 100, 200);
        assertEquals(false, result);
    }

    @Test
    void testReduceResource() {
        Empire empire = mock(Empire.class);
        empireService.reduceResource(empire, 50, 100, 200);
        verify(empire).setGold(anyInt());
        verify(empire).setIron(anyInt());
        verify(empire).setWood(anyInt());
        verify(empireService.getEmpireRepository()).save(any());
    }

    @Test
    void testPayMine() {
        Empire empire = mock(Empire.class);
        empireService.payMine(empire, "gold");
        verify(empire).setGold(anyInt());
        verify(empireService.getEmpireRepository()).save(any());
    }

    @Test
    void testSubmitBuyMine() {
        Empire empire = new Empire();
        Mine mine = new Mine();
        String result = empireService.submitBuyMine(empire, mine, "gold", model);
        assertEquals("redirect:/empire/show", result);
    }

    @Test
    void testCheckIfCanBuyMineWhenReturnTrue() {
        Empire empire = new Empire();
        empire.setGold(100);
        boolean result = empireService.checkIfCanBuyMine(empire, "gold");
        assertEquals(true, result);
    }

    @Test
    void testCheckIfCanBuyMineWhenReturnFalse() {
        Empire empire = new Empire();
        empire.setGold(50);
        boolean result = empireService.checkIfCanBuyMine(empire, "gold");
        assertEquals(false, result);
    }

    @Test
    void testUpdateEmpire() {
        Empire empire = new Empire();
        empireService.updateEmpire(empire);
        verify(empireService.getEmpireRepository()).save(any());
    }

    @Test
    void testGetEmpireWins() {
        Empire empire = mock(Empire.class);
        empireService.getEmpireWins(empire);
        verify(empire).getWins();
    }
}