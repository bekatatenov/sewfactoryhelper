package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.entity.Fabric;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.service.FabricService;
import com.sewfactoryhelper.sewfactoryhelper.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FabricController {
    @Autowired
    private FabricService fabricService;
    @Autowired
    private SalaryService salaryService;


    @RequestMapping(value = "/fabricall", method = RequestMethod.GET)
    public String allFabric() {
        return "fabricall";
    }

    @RequestMapping(value = "/creatingfabric", method = RequestMethod.GET)
    public ModelAndView creatingFabric() {
        ModelAndView modelAndView = new ModelAndView("fabricpage");
        modelAndView.addObject("fabric", new Fabric());
        return modelAndView;
    }

 @PostMapping(value = "/createdfabric")
 public String createFabric(@ModelAttribute Fabric fabric) {

         this.fabricService.save(fabric);
        return "fabricall";
     }
 }


