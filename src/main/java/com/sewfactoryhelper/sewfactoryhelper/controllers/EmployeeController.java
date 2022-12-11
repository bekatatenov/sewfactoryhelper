package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.dto.EmployeeDto;
import com.sewfactoryhelper.sewfactoryhelper.dto.SalaryDto;
import com.sewfactoryhelper.sewfactoryhelper.entity.Employee;
import com.sewfactoryhelper.sewfactoryhelper.entity.Product;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.service.EmployeeService;
import com.sewfactoryhelper.sewfactoryhelper.service.ProductService;
import com.sewfactoryhelper.sewfactoryhelper.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProductService productService;
    @Autowired
    private SalaryService salaryService;


    @GetMapping("/employeepages")
    public String viewHomePage(Model model) {
        return findPaginatedEmployee(1, "price", "asc", model);
    }

    @RequestMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        EmployeeDto employee = new EmployeeDto();
        model.addAttribute("employee", employee);
        model.addAttribute("products", productService.findAll());
        return "employee/new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute EmployeeDto employee) {
        Salary salary = this.salaryService.findByProductAndRole(employee.getProductId(), employee.getRole());
        Integer amount = employee.getQty() * salary.getPrice();
        Employee newEmployee = null;
        if (employee.getId() != null) {
            newEmployee = this.employeeService.findById(employee.getId());
        }
        if (newEmployee == null) {
            newEmployee = new Employee(employee.getRole(), employee.getQty(), employee.getReject(), salary);
        } else {
            newEmployee.setQty(employee.getQty());
            newEmployee.setRole(employee.getRole());
            newEmployee.setReject(employee.getReject());
        }
        newEmployee.setPrice(salary);
        newEmployee.setSalary(amount);

        this.employeeService.save(newEmployee);
        return "redirect:/employeepages";
    }

    @GetMapping("/showEmployeeFormForUpdate/{id}")
    public String showEmployeeFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setRole(employee.getRole());
        dto.setProductId(employee.getPrice().getProduct().getId());
        dto.setQty(employee.getQty());
        dto.setReject(employee.getReject());

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", dto);
        model.addAttribute("products", productService.findAll());
        return "employee/update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long id) {

        // call delete employee method
        this.employeeService.deleteEmployee(id);
        return "redirect:/employeepages";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginatedEmployee(@PathVariable (value = "pageNo") int pageNo,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir,
                                        Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginatedEmployee(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployee = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployee", listEmployee);
        return "employee/index";
    }
}



//    @RequestMapping(value = "/creatingemployee", method = RequestMethod.GET)
//    public ModelAndView creatingEmployee() {
//        ModelAndView modelAndView = new ModelAndView("employeepage");
//        modelAndView.addObject("employee", new Employee());
//        return modelAndView;
//    }

//   @PostMapping(value = "/createdemployee")
//   public String createEmployee(@ModelAttribute Employee employee) {

//       this.employeeService.save(employee);
//       return "employeeall";
//   }


//    @RequestMapping(value = "/employeeall", method = RequestMethod.GET)
//    public String allEmployee() {
//        return "employeeall";
//    }