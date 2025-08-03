package com.dinesh.mvcproductmanagementsystem.controller;

import com.dinesh.mvcproductmanagementsystem.model.Product;
import com.dinesh.mvcproductmanagementsystem.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Displays a list of all products. This will be our home page.
     */
    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products"; // Returns templates/products.html
    }

    /**
     * Shows the form for adding a new product.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product"; // Returns templates/add-product.html
    }

    /**
     * Processes the form submission for adding or updating a product.
     */
    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // If there are validation errors, return to the form page
            // The view name depends on whether we are adding or editing
            return product.getId() == 0 ? "add-product" : "edit-product";
        }
        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Product saved successfully!");
        return "redirect:/products"; // Redirect to the product list page
    }

    /**
     * Shows the form for editing an existing product.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        return productService.findProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "edit-product"; // Returns templates/edit-product.html
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
                    return "redirect:/products";
                });
    }

    /**
     * Deletes a product by its ID.
     */
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("successMessage", "Product deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product.");
        }
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String viewProductDetails(@PathVariable("id") Integer id,Model model,RedirectAttributes redirectAttributes){
        return productService.findProductById(id)
                .map(product ->
                {
                    model.addAttribute("product", product);
                    return "view-product"; // Returns templates/view-product.html
                })
                .orElseGet(()->{
                    redirectAttributes.addFlashAttribute("errorMessage", "Product not found!");
                    return "redirect:/products";
                });
    }


    @GetMapping("/search")
    public String showSearchForm() {
        // This will render a new HTML file we are about to create.
        return "search-form";
    }

    @GetMapping("/results")
    public String handleSearch(@RequestParam(required = false) Integer id,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String category,
                               @RequestParam(required = false) Integer quantity,
                               Model model) {
        List<Product> products = productService.searchProductsByExample(id, name, category, quantity);
        model.addAttribute("products", products);
        // FIX: Corrected typo to match the Thymeleaf template (`searchPerformed`)
        model.addAttribute("searchPerformed", true); // To indicate that a search has been performed
        return "search-results";
    }



}
