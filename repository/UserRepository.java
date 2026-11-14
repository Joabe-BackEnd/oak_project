package com.system.piratas.oak.repository;

import com.system.piratas.oak.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    @EntityGraph(attributePaths = "access")
    Optional<Users> findByName(String name);

}
