package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepo extends JpaRepository<Skill, Integer> {
   @Query(value = "select * from skill where basic_id=?1",nativeQuery = true)
    List<Skill> findByBasicInformationId(Integer basicInformationId);
}
