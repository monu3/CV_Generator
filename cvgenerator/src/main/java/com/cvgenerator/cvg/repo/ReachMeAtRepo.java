package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.ReachMeAt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReachMeAtRepo extends JpaRepository<ReachMeAt, Integer> {
    @Query(value = "select * from reach_me_at where basic_id=?1", nativeQuery = true)
    List<ReachMeAt> findByBasicInformationId(Integer basicInformationId);
}
