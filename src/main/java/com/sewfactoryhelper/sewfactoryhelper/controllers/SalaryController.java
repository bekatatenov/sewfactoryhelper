package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.dto.EmployeeDto;
import com.sewfactoryhelper.sewfactoryhelper.dto.SalaryDto;
import com.sewfactoryhelper.sewfactoryhelper.entity.Employee;
import com.sewfactoryhelper.sewfactoryhelper.entity.Product;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.service.ProductService;
import com.sewfactoryhelper.sewfactoryhelper.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @Autowired
    ProductService productService;


    // display list of salary
    @GetMapping("/salarypages")
    public String viewHomePage(Model model) {
        return findPaginatedSalary(1, "product", "asc", model);
    }

    @GetMapping("/showNewSalaryForm")
    public String showNewSalaryForm(Model model) {
        SalaryDto salary = new SalaryDto();
        model.addAttribute("salary", salary);
        model.addAttribute("products", productService.findAll());
        return "salary/new_salary";
    }

  //  @PostMapping("/saveSalary")
  //  public String saveSalary(@ModelAttribute SalaryDto salary) {
  //      Product byId = productService.findById(salary.getProductId());
  //      Salary newSalary = null;
  //      if (salary.getId() != null) {
  //          newSalary = this.salaryService.findById(salary.getId());
  //      }
  //      if (newSalary == null) {
  //          newSalary = new Salary(salary.getRole(), salary.getPrice(), byId);
  //      } else {
  //          newSalary.setId(salary.getProductId());
  //          newSalary.setRole(salary.getRole());
  //          newSalary.setPrice(salary.getPrice());
  //      }
  //      this.salaryService.save(newSalary);
  //      return "redirect:/salarypages";
  //  }


    @GetMapping("/showSalaryFormForUpdate/{id}")
    public String showFormForSalaryUpdate(@PathVariable(value = "id") long id, Model model) {

        // get salary from the service
        Salary salary = salaryService.getSalaryById(id);
        SalaryDto dto = new SalaryDto();
        dto.setRole(salary.getRole());
        dto.setProductId(salary.getProduct().getId());
        dto.setPrice(salary.getPrice());

        // set salary as a model attribute to pre-populate the form
        model.addAttribute("salary", dto);
        model.addAttribute("products", productService.findAll());
        return "salary/update_salary";
    }

    @GetMapping("/deleteSalary/{id}")
    public String deleteSalary(@PathVariable(value = "id") long id) {

        // call delete salary method
        this.salaryService.deleteSalary(id);
        return "redirect:/salarypages";
    }

    @GetMapping("/pageSalary/{pageNo}")
    public String findPaginatedSalary(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir,
                                      Model model) {
        int pageSize = 5;

        Page<Salary> page = salaryService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Salary> listSalary = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listSalary", listSalary);
        return "salary/index";
    }

}
