package com.example.ImperiaConquest.Mine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MineRepository extends CrudRepository<Mine, Long> {
    public Mine getMineById(@Param("mine_id") Long id);
}
