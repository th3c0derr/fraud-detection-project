package com.fintech.fraud.config;

import com.fintech.fraud.domain.service.TransactionService;
import com.fintech.fraud.dto.TransactionRequest;
import com.fintech.fraud.entity.Account;
import com.fintech.fraud.entity.Customer;
import com.fintech.fraud.repository.AccountRepository;
import com.fintech.fraud.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;


// IDEMPOTENT DATASEEDER

@Component
public class DataSeeder implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public DataSeeder(CustomerRepository customerRepository, AccountRepository accountRepository, TransactionService transactionService) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void run(String... args) {

        //1. Customer
        Customer customer = getOrCreateCustomer("John Doe", "johndoe@demo.com", "ES");
        Customer customer2 = getOrCreateCustomer("Gina Davis", "ginadavis@demo.com", "US");
        Customer customer3 = getOrCreateCustomer("Arnold Schwarzenegger", "t800@demo.com", "NG");

        //2. Account
        Account account = getOrCreateAccount(customer, "ES91TEST0000000001");
        Account account2 = getOrCreateAccount(customer2, "US65TEST8576453625");
        Account account3 = getOrCreateAccount(customer3, "NG23TEST3216549871");

        //3. Transactions
        seedTransaction(customer.getId(), account.getId(), 200, customer.getCountry());
        seedTransaction(customer2.getId(), account2.getId(), 1500, customer2.getCountry());
        seedTransaction(customer3.getId(), account3.getId(), 5000, customer3.getCountry());
        seedTransaction(customer.getId(), account.getId(), 9000, customer.getCountry());
        seedTransaction(customer2.getId(), account2.getId(), 15000, customer2.getCountry());
        seedTransaction(customer3.getId(), account3.getId(), 25000, customer3.getCountry());
    }

    // CUSTOMER SAFE CREATION
    private Customer getOrCreateCustomer(String name, String email, String country){
        return customerRepository.findByEmail(email).orElseGet(() -> {
           Customer c = new Customer();
           c.setName(name);
           c.setEmail(email);
           c.setCountry(country);
           return customerRepository.save(c);
        });
    }

    // ACCOUNT SAFE CREATION
    private Account getOrCreateAccount(Customer customer, String iban){
        return accountRepository.findByIban(iban)
                .orElseGet(() -> {
                    Account a = new Account();
                    a.setCustomer(customer);
                    a.setIban(iban);
                    return accountRepository.save(a);
                });
    }

    // TRANSACTION SAFE CREATION
    private void seedTransaction(UUID customerId, UUID accountId, double amount, String country){
        TransactionRequest request = new TransactionRequest();

        request.setCustomerId(customerId);
        request.setAccountId(accountId);
        request.setAmount(BigDecimal.valueOf(amount));
        request.setCurrency("EUR");
        request.setCountry(amount > 10000 ? "NG" : country);
        request.setMerchant("Amazon");
        request.setIpAddress("192.168.30.2");

        transactionService.process(request);
    }
}
