package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.EducationInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EducationInformationRepo extends JpaRepository<EducationInformation, Integer> {
    @Query(value = "select  * from education_information where basic_info_id=?1", nativeQuery = true)
    List<EducationInformation> findByBasicInformationId(Integer basicInformationId);
}
