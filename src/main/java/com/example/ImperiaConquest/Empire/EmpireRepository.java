package com.example.ImperiaConquest.Empire;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmpireRepository extends CrudRepository<Empire, Long>{
    public Empire getEmpireByUserId(@Param("user_id") Long user_id);
}
