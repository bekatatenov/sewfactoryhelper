package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.entity.Employee;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/creatingemployee", method = RequestMethod.GET)
    public ModelAndView creatingEmployee() {
        ModelAndView modelAndView = new ModelAndView("employeepage");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping(value = "/createdemployee")
    public String createEmployee(@ModelAttribute Employee employee) {
        employee.setPrice(employee.getPrice());
        this.employeeService.save(employee);
        return "employeeall";
    }
}
