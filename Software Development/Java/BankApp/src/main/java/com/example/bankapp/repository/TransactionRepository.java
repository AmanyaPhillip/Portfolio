package com.example.bankapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bankapp.model.Transaction;

// Repository interface for Transaction entity, providing CRUD operations and custom queries
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom query method to find all transactions by account ID
    List<Transaction> findByAccountId(Long accountId);
}
