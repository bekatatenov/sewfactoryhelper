package com.sewfactoryhelper.sewfactoryhelper.controllers;

import com.sewfactoryhelper.sewfactoryhelper.dto.FabricDto;
import com.sewfactoryhelper.sewfactoryhelper.entity.Fabric;
import com.sewfactoryhelper.sewfactoryhelper.entity.Salary;
import com.sewfactoryhelper.sewfactoryhelper.service.FabricService;
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
public class FabricController {
    @Autowired
    private FabricService fabricService;
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private ProductService productService;


    @GetMapping("/fabricpages")
    public String viewHomePage(Model model) {
        return findPaginatedFabric(1, "price", "asc", model);
    }

    @RequestMapping("/showNewFabricForm")
    public String showNewFabricForm(Model model) {
        FabricDto fabric = new FabricDto();
        model.addAttribute("fabric", fabric);
        model.addAttribute("products", productService.findAll());
        return "fabric/new_fabric";
    }

    @PostMapping("/saveFabric")
    public String saveFabric(@ModelAttribute FabricDto fabric) {
        Salary salary = this.salaryService.findByProductAndRole(fabric.getProductId(), fabric.getRole());
        Integer amount = fabric.getQty() * salary.getPrice();
        Fabric newFabric = null;
        if (fabric.getId() != null) {
            newFabric = this.fabricService.findById(fabric.getId());
        }
        if (newFabric == null) {
            newFabric = new Fabric(fabric.getRole(), fabric.getFabricType(), fabric.getFabric_web_num(), fabric.getFabric_web_color(), fabric.getLength_web(), fabric.getWaste(), fabric.getExpense(), fabric.getQty(), salary);
        } else {
            newFabric.setRole(fabric.getRole());
            newFabric.setFabricType(fabric.getFabricType());
            newFabric.setFabric_web_num(fabric.getFabric_web_num());
            newFabric.setFabric_web_color(fabric.getFabric_web_color());
            newFabric.setLength_web(fabric.getLength_web());
            newFabric.setWaste(fabric.getWaste());
            newFabric.setExpense(fabric.getExpense());
            newFabric.setQty(fabric.getQty());
        }
        newFabric.setPrice(salary);
        newFabric.setSalary(amount);

        this.fabricService.save(newFabric);
        return "redirect:/fabricpages";
    }

    @GetMapping("/showFabricFormForUpdate/{id}")
    public String showFabricFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        Fabric fabric = fabricService.getFabricById(id);
        FabricDto dto = new FabricDto();
        dto.setId(fabric.getId());
        dto.setRole(fabric.getRole());
        dto.setProductId(fabric.getPrice().getProduct().getId());
        dto.setFabricType(fabric.getFabricType());
        dto.setFabric_web_num(fabric.getFabric_web_num());
        dto.setFabric_web_color(fabric.getFabric_web_color());
        dto.setLength_web(fabric.getLength_web());
        dto.setWaste(fabric.getWaste());
        dto.setExpense(fabric.getExpense());
        dto.setQty(fabric.getQty());

        model.addAttribute("fabric", dto);
        model.addAttribute("products", productService.findAll());
        return "fabric/update_fabric";
    }

    @GetMapping("/deleteFabric/{id}")
    public String deleteFabric(@PathVariable(value = "id") long id) {

        this.fabricService.deleteFabric(id);
        return "redirect:/fabricpages";
    }

    @GetMapping("/pageFabric/{pageNo}")
    public String findPaginatedFabric(@PathVariable(value = "pageNo") int pageNo,
                                      @RequestParam("sortField") String sortField,
                                      @RequestParam("sortDir") String sortDir,
                                      Model model) {
        int pageSize = 5;

        Page<Fabric> page = fabricService.findPaginatedFabric(pageNo, pageSize, sortField, sortDir);
        List<Fabric> listFabric = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listFabric", listFabric);
        return "fabric/index";
    }
}


