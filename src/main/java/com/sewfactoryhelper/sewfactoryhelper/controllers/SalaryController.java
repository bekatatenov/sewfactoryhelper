package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.entity.Users;
import com.sewfactoryhelper.sewfactoryhelper.enums.Product;
import com.sewfactoryhelper.sewfactoryhelper.enums.Role;
import com.sewfactoryhelper.sewfactoryhelper.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SalaryController {
    @Autowired
    SalaryService salaryService;

    @RequestMapping(value = "/salaryall", method = RequestMethod.GET)
    public String allSalary() {
        return "salaryall";
    }

    @RequestMapping(value = "/creatingsalary", method = RequestMethod.GET)
    public ModelAndView creatingsalary() {
        ModelAndView modelAndView = new ModelAndView("salarypage");
        modelAndView.addObject("salary", new Salary());
        return modelAndView;
    }

    @PostMapping(value = "/createsalary")
    public String createSalary(@ModelAttribute Salary salary) {
        salary.setPrice(salary.getPrice());
        this.salaryService.save(salary);
        return "salaryall";
    }

   // @RequestMapping (value = "/createsalary",method = RequestMethod.POST)
   // public  ResponseEntity <Object> createSalary (@RequestBody Salary salary, @PathVariable Product product, Role role) {
   //     try {
   //         return new ResponseEntity<Object>(salaryService.createSalary(product, role), HttpStatus.OK);
   //     } catch (Exception e) {
   //         return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
   //     }
   // }
//
    @RequestMapping(value="/deletemethod", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSalary(@PathVariable Long id) {
        try {
            salaryService.deleteSalary(id);
            return new ResponseEntity<Object>("Sucessfully deleted product with id: " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Unable to delete product.", HttpStatus.BAD_REQUEST);
        }
    }


}
