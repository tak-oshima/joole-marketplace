package com.itlize.joolemarketplace.service.impl;

import com.itlize.joolemarketplace.exception.ProjectNotFoundException;
import com.itlize.joolemarketplace.model.Project;
import com.itlize.joolemarketplace.model.User;
import com.itlize.joolemarketplace.repository.ProjectRepository;
import com.itlize.joolemarketplace.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProjectServiceImpl.class})
public class ProjectServiceImplTest {
    @Autowired
    private ProjectService projectService;

    @MockBean
    private ProjectRepository projectRepository;

    @Test
    void createProject() {
        Project project = new Project();
        User user = new User();
        user.setUserName("jmart0");
        project.setUser(user);

        when(projectRepository.save(project)).thenReturn(project);
        Project createdProject = projectService.createProject(project);

        assertEquals(project, createdProject);
        verify(projectRepository).save(project);
    }

    @Test
    void getProjectById() {
        Project project = new Project();
        User user = new User();
        user.setUserName("jmart0");
        project.setUser(user);

        when(projectRepository.findById((Integer) any())).thenReturn(Optional.of(project));
        Optional<Project> foundOptionalProject = projectService.getProjectById(1);

        assertTrue(foundOptionalProject.isPresent());
        assertEquals(project, foundOptionalProject.get());
        verify(projectRepository).findById(1);
    }

    @Test
    void getProjectsByUser() {
        Project project1 = new Project();
        User user1 = new User();
        user1.setUserName("jmart0");
        project1.setUser(user1);
        Project project2 = new Project();
        project2.setUser(user1);
        Project project3 = new Project();
        User user2 = new User();
        user2.setUserName("dgavan1");
        project3.setUser(user2);

        when(projectRepository.findAllByUser(user1)).thenReturn(Arrays.asList(project1, project2));
        List<Project> foundProjects = projectService.getProjectsByUser(user1);

        assertEquals(Arrays.asList(project1, project2), foundProjects);
        verify(projectRepository).findAllByUser(user1);
    }

    @Test
    void getAllProjects() {
        Project project1 = new Project();
        User user1 = new User();
        user1.setUserName("jmart0");
        project1.setUser(user1);
        Project project2 = new Project();
        User user2 = new User();
        user2.setUserName("dgavan1");
        project2.setUser(user2);

        when(projectRepository.findAll()).thenReturn(Arrays.asList(project1, project2));
        List<Project> foundProjects = projectService.getAllProjects();

        assertEquals(Arrays.asList(project1, project2), foundProjects);
        verify(projectRepository).findAll();
    }

    @Test
    void updateProject() {
        Project project = new Project();
        User user = new User();
        user.setUserName("jmart0");
        project.setUser(user);

        when(projectRepository.findById((Integer) any())).thenReturn(Optional.of(project));
        when(projectRepository.save(project)).thenReturn(project);
        Project updatedProject = projectService.updateProject(project);

        assertEquals(project, updatedProject);
        verify(projectRepository).findById((Integer) any());
        verify(projectRepository).save(project);
    }

    @Test
    void updateProjectThatDoesNotExist() {
        Project project = new Project();
        User user = new User();
        user.setUserName("jmart0");
        project.setUser(user);

        when(projectRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectService.updateProject(project));
        verify(projectRepository).findById((Integer) any());
        verify(projectRepository, never()).save(any());
    }

    @Test
    void deleteProject() {
        Project project = new Project();
        User user = new User();
        user.setUserName("jmart0");
        project.setUser(user);

        when(projectRepository.findById((Integer) any())).thenReturn(Optional.of(project));
        projectService.deleteProject(1);

        verify(projectRepository).findById(1);
        verify(projectRepository).deleteById(1);
    }

    @Test
    void deleteProjectThatDoesNotExist() {
        Project project = new Project();
        User user = new User();
        user.setUserName("jmart0");
        project.setUser(user);

        when(projectRepository.findById((Integer) any())).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> projectService.deleteProject(1));
        verify(projectRepository).findById(1);
        verify(projectRepository, never()).deleteById(any());
    }


}
