package com.itlize.joolemarketplace.repository;

import com.itlize.joolemarketplace.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findAllByUser(User user);
}
