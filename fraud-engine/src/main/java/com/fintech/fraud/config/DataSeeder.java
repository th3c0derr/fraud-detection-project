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


        System.out.println(">>> DATA SEEDER RUNNING");

        //1. Customer
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("johndoe@demo.com");
        customer.setCountry("ES");

        Customer customer2 = new Customer();
        customer2.setName("Gina Davis");
        customer2.setEmail("ginadavis@demo.com");
        customer2.setCountry("US");

        Customer customer3 = new Customer();
        customer3.setName("Arnold Schwarzenegger");
        customer3.setEmail("t800@demo.com");
        customer3.setCountry("NG");

        customer = customerRepository.save(customer);
        customer2 = customerRepository.save(customer2);
        customer3 = customerRepository.save(customer3);


        //2. Account
        Account account = new Account();
        account.setCustomer(customer);
        account.setIban("ES91TEST0000000001");

        account = accountRepository.save(account);

        Account account2 = new Account();
        account2.setCustomer(customer2);
        account2.setIban("NG23TEST3216549871");

        account2 = accountRepository.save(account2);


        Account account3 = new Account();
        account3.setCustomer(customer3);
        account3.setIban("US65TEST8576453625");

        account3 = accountRepository.save(account3);

        //3. Transactions
//        createTx(customer.getId(), account.getId(), 200);
//        createTx(customer.getId(), account.getId(), 5000);
//        createTx(customer.getId(), account.getId(), 15000);
//        createTx(customer.getId(), account.getId(), 3000);
//        createTx(customer.getId(), account.getId(), 25000);

        createTx(customer.getId(), account.getId(), 200, customer.getCountry());      // LOW
        createTx(customer2.getId(), account2.getId(), 1500, customer2.getCountry());   // LOW
        createTx(customer3.getId(), account3.getId(), 5000, customer3.getCountry());   // MEDIUM
        createTx(customer.getId(), account.getId(), 9000, customer.getCountry());     // MEDIUM
        createTx(customer2.getId(), account2.getId(), 15000, customer2.getCountry());  // HIGH
        createTx(customer3.getId(), account3.getId(), 25000, customer3.getCountry());  // HIGH
    }

    private void createTx(UUID customerId, UUID accountId, double amount, String country){
        TransactionRequest request = new TransactionRequest();

        request.setCustomerId(customerId);
        request.setAccountId(accountId);
        request.setAmount(BigDecimal.valueOf(amount));
        request.setCurrency("EUR");
        request.setCountry(amount > 10000 ? "NG" : country);
        request.setMerchant("Amazon");
        request.setIpAdress("192.168.30.2");

        transactionService.process(request);
    }
}
