package com.zy.study.springboot.repository;

import com.zy.study.springboot.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zy on 17-8-28.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
