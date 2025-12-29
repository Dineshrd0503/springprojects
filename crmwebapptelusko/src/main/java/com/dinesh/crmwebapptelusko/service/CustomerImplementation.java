package com.dinesh.crmwebapptelusko.service;

import com.dinesh.crmwebapptelusko.model.Customer;
import com.dinesh.crmwebapptelusko.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImplementation implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerImplementation(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomerInfo() {
        return customerRepository.findAll();
    }

    @Override
    public void registerCustomer(Customer cust) {
        customerRepository.save(cust);
    }

    @Override
    public boolean updateCustomer(Customer cust) {
       if(customerRepository.existsById(cust.getId())) {
           customerRepository.save(cust);
           return true;
       }
       return false;
    }

    @Override
    public boolean deleteCustomer(Integer id) {
        if(customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.orElse(null);
    }
}
