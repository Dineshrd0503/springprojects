package com.dinesh.crmwebapptelusko.service;

import com.dinesh.crmwebapptelusko.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomerInfo();

    public void registerCustomer(Customer cust);

    public boolean updateCustomer(Customer cust);

    boolean deleteCustomer(Integer id);

    Customer getCustomerById(Integer id);
}
