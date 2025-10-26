package com.dinesh.springaopdemo.service;


import com.dinesh.springaopdemo.annotation.Secured;
import com.dinesh.springaopdemo.model.Account;
import com.dinesh.springaopdemo.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private BankRepository bankRepository;
    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    public Account createAccount(Long id,String name,double balance){
        Account account=new Account(id,name,balance,true);
        return bankRepository.save(account);
    }

    public List<Account> getAllCustomers(){
        return bankRepository.findAll();
    }


    public Optional<Account> getAccountById(Long id){
        return bankRepository.findById(id);
    }

    public Optional<Double> getBalance(Long id){
        return getAccountById(id).map(Account::getBalance);
    }

    public Optional<String> DeleteAccount(Long id){
      if(bankRepository.existsById(id)) {
          bankRepository.deleteById(id);
          return Optional.of("Account deleted");
      }
      return Optional.empty();
    }


    @Secured
    public Account deposit(Long id,double amount) {
        Account acc = getAccountById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        acc.setBalance(acc.getBalance() + amount);
        return bankRepository.save(acc);

    }

    @Secured
    public Account withdraw(Long id,double amount) {
        Account acc = getAccountById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(acc.getBalance()<amount)
            throw new RuntimeException("insufficient balance");
        acc.setBalance(acc.getBalance()-amount);
        return bankRepository.save(acc);
    }

    public Account updateAccount(Long id, String name, double balance) {
        Account account = getAccountById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setName(name);
        account.setBalance(balance);
        account.setActive(true);
        return bankRepository.save(account);
    }

    @Secured
    public void transfer(Long fromId, Long toId, double amount) {
        withdraw(fromId, amount);
        deposit(toId, amount);
    }
}
