package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.Projects;
import com.cvgenerator.cvg.entity.ReachMeAt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectsRepo extends JpaRepository<Projects, Integer> {

    //@Query(value = "select * from projects join public.experience e on projects.experience_id = e.experience_id where basic_id = ?1;", nativeQuery = true)


    @Query(value = "select * from projects where experience_id=?1", nativeQuery = true)
    List<Projects> findByExperienceId(Integer experienceId);

}
