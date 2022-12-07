package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.entity.Product;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/productpages")
    public String viewHomePage(Model model) {
        return findPaginated(1, "name", "asc", model);
    }

    @RequestMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";
    }

    @PostMapping("/saveProduct")
    public String saveSalary(@ModelAttribute Product product) {
        this.productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get salary from the service
        Product product = productService.getProductById(id);

        // set salary as a model attribute to pre-populate the form
        model.addAttribute("product", product);
        return "update_product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteSalary(@PathVariable (value = "id") long id) {

        // call delete salary method
        this.productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Product> page = productService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Product> listProduct = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProduct", listProduct);
        return "index";
    }
}
