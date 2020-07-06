package com.bismuth.bismuth.project

import com.bismuth.bismuth.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectService {

    @Autowired
    lateinit var projectRepository: ProjectRepository;

    fun create(project: Project, user: User): Project {
        project.project_id = UUID.randomUUID();
        project.createdBy = user.user_id;
        project.ownedBy = user.user_id;
        ProjectBO.validate(project);
        return projectRepository.save(project);
    }

    fun getAllVisibleByUser(user: User): List<Project> = projectRepository.getByUserId(user.user_id as UUID);

    fun searchByDescription(user: User, searchString: String): List<Project> =
            projectRepository.getByDescription(user.user_id as UUID, searchString);

}