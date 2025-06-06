package org.quantum.usm.repository;

import java.util.Optional;

import org.quantum.usm.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
