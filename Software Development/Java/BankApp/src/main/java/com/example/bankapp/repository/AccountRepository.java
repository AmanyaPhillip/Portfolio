package com.example.bankapp.repository;

import com.example.bankapp.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository interface for Account entity, providing CRUD operations and custom queries
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Custom query method to find an account by username
    Optional<Account> findByUsername(String username);
}
