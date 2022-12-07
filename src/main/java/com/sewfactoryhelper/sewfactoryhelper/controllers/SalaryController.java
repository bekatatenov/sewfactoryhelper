package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
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

    //@RequestMapping(value = "/salaryall", method = RequestMethod.GET)
    //public String allSalary() {
    //    return "salaryall";
    //}
//
    //@RequestMapping(value = "/creatingsalary", method = RequestMethod.GET)
    //public ModelAndView creatingsalary() {
    //    ModelAndView modelAndView = new ModelAndView("salarypage");
    //    modelAndView.addObject("salary", new Salary());
    //    return modelAndView;
    //}
//
// display list of salary
    @GetMapping("/salarypages")
    public String viewHomePage(Model model) {
        return findPaginatedSalary(1, "product", "asc", model);
    }

    @GetMapping("/showNewSalaryForm")
    public String showNewSalaryForm(Model model) {
        Salary salary = new Salary();
        model.addAttribute("salary", salary);
        return "salary/new_salary";
    }

    @PostMapping("/saveSalary")
    public String saveSalary(@ModelAttribute Salary salary) {
        this.salaryService.save(salary);
        return "redirect:/";
    }

    @RequestMapping("/showFormForUpdate/{id}")
    	public String showFormForSalaryUpdate(@PathVariable ( value = "id") int id, Model model) {

    		// get salary from the service
    		Salary salary = salaryService.getSalaryById(id);

    		// set salary as a model attribute to pre-populate the form
    		model.addAttribute("salary", salary);
    		return "salary/update_salary";
    	}

    	@GetMapping("/deleteSalary/{id}")
    	public String deleteSalary(@PathVariable (value = "id") long id) {

    		// call delete salary method
    		this.salaryService.deleteSalary(id);
    		return "redirect:/";
    	}

    @GetMapping("/pageSalary/{pageNo}")
    public String findPaginatedSalary(@PathVariable (value = "pageNo") int pageNo,
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
