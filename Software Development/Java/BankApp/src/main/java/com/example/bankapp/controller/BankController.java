package com.example.bankapp.controller;
import com.example.bankapp.model.Account;
import com.example.bankapp.service.AccountService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BankController {

    @Autowired
    private AccountService accountService;

    // Display the dashboard with account details
    @GetMapping("/dashboard")
    public String dashboard(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUsername(username);
        model.addAttribute("account", account);
        return "dashboard";
    }

    // Show the registration form
    @GetMapping("/register")
    public String showRegistrationForm(){
        return "register";
    }

    // Handle user registration
    @PostMapping("/register")
    public String registerAccount(@RequestParam String username, @RequestParam String password, Model model){
        try{ 
            accountService.registerAccount(username, password);
            return "redirect:/login";
        } catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // Show the login page
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // Handle deposit requests
    @PostMapping("/deposit")
    public String deposit(@RequestParam BigDecimal amount){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUsername(username);
        accountService.deposit(account, amount);
        return "redirect:/dashboard";
    }

    // Handle withdrawal requests
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam BigDecimal amount, Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUsername(username);
        try {
            accountService.withdraw(account, amount);
        } catch (Exception e) {
            // If withdrawal fails (e.g., insufficient funds), show error on dashboard
            model.addAttribute("error", e.getMessage());
            model.addAttribute("account", account);
            return "dashboard";
        }
        return "redirect:/dashboard";
    }

    // Display transaction history for the logged-in user
    @GetMapping("/transactions")
    public String transactionHistory(Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUsername(username);
        model.addAttribute("transactions", accountService.getTransactionHistory(account));
        return "transactions";
    }

    // Handle fund transfer between users
    @PostMapping("/transfer")
    public String transfer(@RequestParam String toUsername, @RequestParam BigDecimal amount, Model model){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Account fromAccount = accountService.findAccountByUsername(username);
        try {
            accountService.transferAmount(fromAccount, toUsername, amount);
        } catch (Exception e) {
            // If transfer fails (e.g., insufficient funds or recipient not found), show error
            model.addAttribute("error", e.getMessage());
            model.addAttribute("account", fromAccount);
            return "dashboard";
        }
        return "redirect:/dashboard";
    }

}
