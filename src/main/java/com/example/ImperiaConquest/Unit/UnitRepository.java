package com.example.ImperiaConquest.Unit;

import com.example.ImperiaConquest.Empire.Empire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findByEmpireAndType(Empire empire, String type);

//    Optional<Integer> findLevelByEmpireAndType(@Param("empire") Empire empire, @Param("type") String type);
}
