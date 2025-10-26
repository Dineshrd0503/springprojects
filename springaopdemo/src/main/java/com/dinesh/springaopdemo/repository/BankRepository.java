package com.dinesh.springaopdemo.repository;

import com.dinesh.springaopdemo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankRepository extends JpaRepository<Account,Long> {

}
