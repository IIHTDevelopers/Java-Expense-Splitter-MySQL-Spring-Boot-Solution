package com.expensesplitter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensesplitter.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
