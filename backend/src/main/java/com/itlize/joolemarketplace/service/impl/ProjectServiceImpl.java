package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProjectNotFoundException;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.repository.ProjectRepository;
import com.itlize.joolemarketplace.service.ProjectService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> getProjectById(Integer projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public List<Project> getProjectsByUser(User user) {
        return projectRepository.findAllByUser(user);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project updateProject(Project project) {
        if (!projectRepository.findById(project.getProjectId()).isPresent()) {
            throw new ProjectNotFoundException(project.getProjectId());
        }
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Integer projectId) {
        if (!projectRepository.findById(projectId).isPresent()) {
            throw new ProjectNotFoundException(projectId);
        }
        projectRepository.deleteById(projectId);
    }
}
