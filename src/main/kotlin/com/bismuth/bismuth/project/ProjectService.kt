package com.bismuth.bismuth.project

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.framework.data.PageCommons
import com.bismuth.bismuth.framework.exception.EntityNotFoundException
import com.bismuth.bismuth.project.events.ProjectEventService
import com.bismuth.bismuth.project.visibility.ProjectVisibilityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional


@Service
@Transactional
class ProjectService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var projectVisibilityService: ProjectVisibilityService;

    @Autowired
    lateinit var projectEventService: ProjectEventService;

    @Autowired
    lateinit var projectRepository: ProjectRepository;

    fun create(project: Project): Project {
        val user = Auth.getAuthenticatedUser(request);
        project.projectId = UUID.randomUUID();
        project.createdBy = user.user_id;
        project.ownedBy = user.user_id;
        ProjectBO.validate(project);
        val newProject = projectRepository.save(project);
        projectEventService.createNewProjectEvent(project);
        projectVisibilityService.createNewProjectVisibility(project);
        return newProject;
    }

    fun getById(projectId: UUID): Project {
        val project = projectRepository.findById(projectId);
        if (!project.isPresent)
            throw EntityNotFoundException("We were unable to find a project with such identifier.");
        return project.get();
    }

    fun getAllVisibleForUser(pageable: Pageable): Page<Project> {
        val user = Auth.getAuthenticatedUser(request);
        val listOfProjects = projectRepository.getByUserId(user.user_id!!);
        return PageCommons.getPaged(pageable, listOfProjects);
    }

    fun searchByDescription(searchString: String): List<Project> {
        val user = Auth.getAuthenticatedUser(request);
        return projectRepository.getByDescription(user.user_id!!, "%${searchString}%");
    }

    fun update(project: Project, projectId: UUID): Project {
        val originalProject = getById(projectId).copy();
        val user = Auth.getAuthenticatedUser(request);
        ProjectBO.validate(project);
        ProjectGuardian.onUser(user).protectEditingOf(originalProject);
        projectRepository.save(project);
        projectEventService.createEventsForUpdate(project, originalProject);
        return project;
    }

    fun delete(projectId: UUID) {
        val project = getById(projectId);
        val user = Auth.getAuthenticatedUser(request);
        ProjectGuardian.onUser(user).protectDeletionOf(project);
        project.isSoftdeleted = true;
        project.softdeletedAt = Date();
        update(project, projectId);
    }

}