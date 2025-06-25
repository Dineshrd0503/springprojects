package com.dinesh.springbootmvcproject.controller;

import com.dinesh.springbootmvcproject.model.Product;
import com.dinesh.springbootmvcproject.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showHome(Model model){
        model.addAttribute("products", productService.displayProducts());
        model.addAttribute("product",new Product());
        return "index";

    }

    @PostMapping("/product/add")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model){
        if(result.hasErrors())
        {
            model.addAttribute("products", productService.displayProducts());
            return "index";
        }
        try {
            productService.createProduct(product);
        }
        catch(IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("products", productService.displayProducts());
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable int id,Model model){
        Product product = productService.findProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product with ID " + id + " not found.");
            model.addAttribute("products",productService.displayProducts());
            model.addAttribute("product", new Product());
            return "index";
        }
        model.addAttribute("product", product);
        model.addAttribute("products", productService.displayProducts());
        return "index";
    }
    @PostMapping("/product/update/{id}")
    public String updateProduct(@PathVariable int id, @Valid @ModelAttribute Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.displayProducts());
            return "index";
        }
        try {
            product.setId(id);
            productService.updateProduct(product, id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("products", productService.displayProducts());
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteproduct(@PathVariable int id,Model model){
        try{
            productService.deleteProduct(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("products", productService.displayProducts());
            model.addAttribute("product", new Product());
            return "index";
        }
        return "redirect:/";

    }
}
