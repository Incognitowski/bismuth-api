package com.bismuth.bismuth.project

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.framework.data.PageCommons
import com.bismuth.bismuth.framework.exception.EntityNotFoundException
import com.bismuth.bismuth.project.events.ProjectEventService
import com.bismuth.bismuth.project.visibility.ProjectVisibility
import com.bismuth.bismuth.project.visibility.ProjectVisibilityService
import com.bismuth.bismuth.user.User
import com.bismuth.bismuth.user.UserService
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

    @Autowired
    lateinit var userRepository: UserService;

    fun create(project: Project): Project {
        val user = Auth.getAuthenticatedUser(request);
        project.projectId = UUID.randomUUID();
        project.createdBy = user;
        project.ownedBy = user;
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
        val listOfProjects = projectRepository.getByUserId(user.userId!!);
        listOfProjects.forEach {
            it.relationShipWithCurrentUser = projectVisibilityService.getVisibilityOf(user, it);
        }
        return PageCommons.getPaged(pageable, listOfProjects);
    }

    fun searchByDescription(searchString: String): List<Project> {
        val user = Auth.getAuthenticatedUser(request);
        return projectRepository.getByDescription(user.userId!!, "%${searchString}%");
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

    fun getUsersRelatedToProject(projectId: UUID): List<User> {
        val project = getById(projectId);
        val user = Auth.getAuthenticatedUser(request);
        ProjectGuardian.onUser(user).protectAccessToRelatedUsersOf(project);
        val usersRelatedToProject: List<User> = userRepository.getUsersRelatedToProject(project);
        usersRelatedToProject.forEach {
            it.relationshipWithCurrentProject = projectVisibilityService.getVisibilityOf(it, project);
        }
        return usersRelatedToProject;
    }

    fun attachUserToProject(projectId: UUID, projectVisibility: ProjectVisibility): ProjectVisibility {
        val project = getById(projectId);
        val user = Auth.getAuthenticatedUser(request);
        ProjectGuardian.onUser(user).protectAccessToRelatedUsersOf(project);
        return projectVisibilityService.create(projectVisibility);
    }

}