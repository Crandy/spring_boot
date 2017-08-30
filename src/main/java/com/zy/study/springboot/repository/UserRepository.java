package com.zy.study.springboot.repository;

import com.zy.study.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zy on 17-8-28.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
