package com.example.bankapp.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bankapp.model.Account;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Find an account by username, throw exception if not found
    public Account findAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // Register a new account with encoded password and zero balance
    public Account registerAccount(String username, String password) {
        if (accountRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    // Deposit amount to account and record the transaction
    public void deposit(Account account, BigDecimal amount){
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction(amount, "DEPOSIT", LocalDateTime.now(), account);
        transactionRepository.save(transaction);
    }

    // Withdraw amount from account if sufficient funds, and record the transaction
    public void withdraw(Account account, BigDecimal amount){
        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);

        Transaction transaction = new Transaction(amount, "WITHDRAW", LocalDateTime.now(), account);
        transactionRepository.save(transaction);
    }

    // Retrieve transaction history for a given account
    public List<Transaction> getTransactionHistory(Account account) {
        return transactionRepository.findByAccountId(account.getId());
    }

    // Load user details for authentication (Spring Security)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = findAccountByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User or password not found");
        }
        return new Account(account.getUsername(), account.getPassword(), account.getBalance(),account.getTransactions(),authorities());
    }

    // Define default authorities/roles for users
    public Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("User"));
    }

    // Transfer amount from one account to another, update balances, and record transactions
    public void transferAmount(Account fromAccount, String toUsername, BigDecimal amount) {
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        Account toAccount = accountRepository.findByUsername(toUsername).orElseThrow(() -> new RuntimeException("Recipient not found"));
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountRepository.save(fromAccount);
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);
        
        // Record debit transaction for sender
        Transaction debittransaction = new Transaction(amount, "TRANSFER OUT TO "+toAccount.getUsername(), LocalDateTime.now(), fromAccount);
        transactionRepository.save(debittransaction);

        // Record credit transaction for recipient
        Transaction credittransaction = new Transaction(amount, "TRANSFER IN TO "+fromAccount.getUsername(), LocalDateTime.now(), toAccount);
        transactionRepository.save(credittransaction);
    }

}
