package com.expensesplitter.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expensesplitter.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findByPaidById(Long userId);

	List<Expense> findBySharedWithId(Long userId);

	@Query("SELECT e FROM Expense e WHERE e.paidBy.id = :payerId OR e.id IN (SELECT e2.id FROM Expense e2 JOIN e2.sharedWith u WHERE u.id = :participantId)")
	List<Expense> findExpensesInvolvingUsers(Long payerId, Long participantId);
}
