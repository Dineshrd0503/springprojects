package com.dinesh.springaopdemo.controller;


import com.dinesh.springaopdemo.model.Account;
import com.dinesh.springaopdemo.service.BankService;
import org.apache.tomcat.util.http.parser.AcceptEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bank")
public class BankController {
    private BankService bankService;
    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }
    @GetMapping("/viewCustomers")
    public ResponseEntity<List<Account>> viewCustomers(){

        return ResponseEntity.ok(bankService.getAllCustomers());
    }

    @GetMapping("/viewCustomerById/{id}")
    public ResponseEntity<Optional<Account>> viewCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(bankService.getAccountById(id));
    }

    @RequestMapping("/addCustomer")
    public ResponseEntity<Account> addCustomer(@RequestBody Account account){
        return ResponseEntity.ok(bankService.createAccount(account.getId(),account.getName(),account.getBalance()));
    }

    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Account> updateCustomer(@PathVariable Long id,@RequestBody Account account){
        return  ResponseEntity.ok(bankService.updateAccount(id,account.getName(),account.getBalance()));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<String>> deleteCustomer(@PathVariable Long id) {
        return ResponseEntity.ok(bankService.DeleteAccount(id));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Optional<Double>> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(bankService.getBalance(id));
    }

    @PostMapping("/transfer/{fromId}/{toId}/{amount}")
    public ResponseEntity<String> transfer(@PathVariable Long fromId, @PathVariable Long toId, @PathVariable double amount) {
        bankService.transfer(fromId, toId, amount);
        return ResponseEntity.ok("Transfer successful");
    }


}