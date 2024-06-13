package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.entity.EducationInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BasicInformationRepo extends JpaRepository<BasicInformation, Integer> {
    @Query(value = "select  * from educationInfo where basic_id=?1", nativeQuery = true)
    List<EducationInformation> findByBasicInformationId(Integer basicInformationId);
}
