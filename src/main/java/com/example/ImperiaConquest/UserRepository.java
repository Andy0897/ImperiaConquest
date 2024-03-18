package com.example.ImperiaConquest;

import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
