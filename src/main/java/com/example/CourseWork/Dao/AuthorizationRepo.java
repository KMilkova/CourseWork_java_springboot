package com.example.CourseWork.Dao;

import com.example.CourseWork.Model.Entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorizationRepo extends JpaRepository<Authorization, Integer> {

    Optional<Authorization> findByUsername(String username);

    List<Authorization> findAllByIdNot(Integer id);
    boolean existsByUsername(String username);
}
