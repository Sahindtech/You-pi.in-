package com.youpi.youpi.repository;

import com.youpi.youpi.entity.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorsRepository extends JpaRepository<Operators, Long> {
}
