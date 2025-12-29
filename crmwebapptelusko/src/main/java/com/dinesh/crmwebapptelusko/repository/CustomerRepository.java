package com.dinesh.crmwebapptelusko.repository;

import com.dinesh.crmwebapptelusko.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
