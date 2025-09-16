package com.youpi.youpi.repository;

import com.youpi.youpi.entity.EmiPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmiPlansRepository extends JpaRepository<EmiPlans, Long> {
}
