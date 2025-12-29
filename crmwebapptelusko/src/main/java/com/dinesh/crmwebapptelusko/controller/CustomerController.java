package com.dinesh.crmwebapptelusko.controller;

import com.dinesh.crmwebapptelusko.model.Customer;
import com.dinesh.crmwebapptelusko.service.CustomerImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerImplementation customerImplementation;

    @Autowired
    public CustomerController(CustomerImplementation customerImplementation) {
        this.customerImplementation = customerImplementation;
    }
    
    @GetMapping("/showForm")
    public String showForm(Model model){
        model.addAttribute("customer", new Customer());
        return "addform";
    }

    @PostMapping("/registerCustomer")
    public String registerCustomer(@ModelAttribute("customer") Customer cust, RedirectAttributes redirectAttributes) {
        customerImplementation.registerCustomer(cust);
        redirectAttributes.addFlashAttribute("successMessage", "Customer details added successfully!");
        return "redirect:/allcustomers";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/allcustomers")
    public String getAllCustomers(Model model){
        List<Customer> clist=customerImplementation.getCustomerInfo();
        model.addAttribute("customers",clist);
        clist.forEach(System.out::println);
        return "customerinfo";
    }

    @GetMapping("/updateForm/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        Customer customer = customerImplementation.getCustomerById(id);
        model.addAttribute("updatedcustomer", customer);
        return "updateform";
    }

    @PostMapping("/updateCustomer/{id}")
    public String updateCustomer(@ModelAttribute("updatedcustomer") Customer cust, @PathVariable Integer id, RedirectAttributes redirectAttributes){
        cust.setId(id); // Ensure the ID from the path is used
        boolean isUpdated = customerImplementation.updateCustomer(cust);
        if (isUpdated) {
            redirectAttributes.addFlashAttribute("successMessage", "Customer details updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Customer with ID " + id + " not found!");
        }
        return "redirect:/allcustomers";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean result=customerImplementation.deleteCustomer(id);
        if(result) {
            redirectAttributes.addFlashAttribute("successMessage", "Customer with ID " + id + " deleted successfully!");
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "Customer with ID " + id + " not found!");
        }
        return "redirect:/allcustomers";
    }

    @GetMapping("/wishes/{id}")
    @ResponseBody
    public ResponseEntity<?> wishCustomer(@PathVariable Integer id){
       Customer c=customerImplementation.getCustomerById(id);
       if(c==null)
           return new ResponseEntity<String>("custome rnot found",HttpStatus.BAD_REQUEST);
       return new ResponseEntity<Customer>(c,HttpStatus.OK);
    }



}
