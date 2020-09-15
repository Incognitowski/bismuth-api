package com.bismuth.bismuth.project.visibility

import com.bismuth.bismuth.framework.authentication.Auth
import com.bismuth.bismuth.framework.exception.EntityNotFoundException
import com.bismuth.bismuth.project.Project
import com.bismuth.bismuth.project.ProjectRepository
import com.bismuth.bismuth.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class ProjectVisibilityService {

    @Autowired
    lateinit var request: HttpServletRequest;

    @Autowired
    lateinit var projectVisibilityRepository: ProjectVisibilityRepository;

    @Autowired
    lateinit var projectRepository: ProjectRepository;

    fun create(projectVisibility: ProjectVisibility): ProjectVisibility {
        projectVisibility.project_visibility_id = UUID.randomUUID();
        return projectVisibilityRepository.save(projectVisibility);
    }

    fun createNewProjectVisibility(project: Project) {
        val user = Auth.getAuthenticatedUser(request);
        create(ProjectVisibility(
                null,
                project.projectId!!,
                user.userId!!,
                "OWNER"
        ));
    }

    fun getVisibilityOf(user: User, project: Project): ProjectVisibility? {
        return projectVisibilityRepository.getVisibilityOf(user.userId!!, project.projectId!!);
    }

    fun getVisibilityOfCurrentUserFrom(project: Project): ProjectVisibility? {
        val user = Auth.getAuthenticatedUser(request);
        return projectVisibilityRepository.getVisibilityOf(user.userId!!, project.projectId!!);
    }

    fun getById(projectVisibilityId: UUID): ProjectVisibility {
        val visibility = projectVisibilityRepository.findById(projectVisibilityId);
        if(!visibility.isPresent)
            throw EntityNotFoundException("No relationship with the project with such ID");
        return visibility.get();
    }

    fun remove(visibility: ProjectVisibility): ProjectVisibility {
        visibility.softdeletedAt = Date();
        visibility.isSoftdeleted = true;
        return projectVisibilityRepository.save(visibility);
    }

    fun update(projectVisibility: ProjectVisibility) : ProjectVisibility {
        return projectVisibilityRepository.save(projectVisibility);
    }

}