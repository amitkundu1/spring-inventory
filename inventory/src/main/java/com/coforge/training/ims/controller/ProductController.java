package com.coforge.training.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coforge.training.ims.model.Product;
import com.coforge.training.ims.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	@RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product p) {
		
		pservice.saveProduct(p);
		
		return "redirect:product";
	}
		
		@RequestMapping("/products")
	    public String viewProducts(Model model) {
	        List<Product> listProducts = pservice.listAll(); // fetch all object/records.
	        model.addAttribute("listProducts", listProducts);

	        return "products";
	    }
		
		@RequestMapping("/edit")
	    public ModelAndView showEditProductPage(@RequestParam("id") int id) {
	        ModelAndView mav = new ModelAndView("edit_product");
	        Product product = pservice.get(id); // Fetch object based on id.
	        mav.addObject("product", product);
	        return mav;
	    }
		
		@RequestMapping("/delete")
	    public String deleteProduct(@RequestParam("id") int id) {
	        
	        
	        
	        
	        pservice.delete(id);
	        return "redirect:products";      
	    }
		
	
}
