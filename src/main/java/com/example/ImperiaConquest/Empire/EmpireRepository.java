package com.example.ImperiaConquest.Empire;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpireRepository extends CrudRepository<Empire, Long> {
    public Empire getEmpireByUserId(@Param("user_id") Long user_id);
    @Query("SELECT e FROM Empire e WHERE e.id <> :idToExclude")
    List<Empire> findAllByIdNot(Long idToExclude);
}
