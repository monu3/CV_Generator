package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepo extends JpaRepository<Experience,Integer> {

    @Query(value = "select * from experience where basic_id=?1", nativeQuery = true)
    List<Experience> findByBasicInformationId(Integer basicInformationId);
}
