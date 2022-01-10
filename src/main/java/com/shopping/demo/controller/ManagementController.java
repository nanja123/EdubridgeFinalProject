package com.shopping.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.demo.FileUploadUtility;
import com.shopping.demo.ProductValidator;
import com.shopping.demo.entities.Category;
import com.shopping.demo.entities.Product;
import com.shopping.demo.repository.ICategoryRepository;
import com.shopping.demo.repository.IProductRepository;
import com.shopping.demo.service.ICategoryService;
import com.shopping.demo.service.IProductService;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@Autowired
	ICategoryService categoryService;
	
	@Autowired
	IProductRepository productRepository;
	
	@Autowired
	IProductService productService;
	
	@Autowired
	ICategoryRepository categoryRepository;
	
	@RequestMapping(value="/products")
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {		

		ModelAndView mv = new ModelAndView("/views/index.jsp");	
			
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");	
		
		Product nProduct = new Product();
		
		//set few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(1);
		
		mv.addObject("product", nProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully!");
			}
			else if(operation.equals("category")){
				mv.addObject("message","Category Submitted Successfully!");
			}
		}
		
		return mv;
		
	}
	
	@RequestMapping("/{id}/product")
	public ModelAndView showEditProduct(@PathVariable int id) {		
		ModelAndView mv = new ModelAndView("/views/index.jsp");	
		
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");	
		
		//fetch the product from database
		Product nProduct = productService.get(id);
		//set the product fetch from database 
		mv.addObject("product", nProduct);
	
		
		return mv;
		
	}
	
	
	//handling product submission
	@PostMapping(value="/products")
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model,
			HttpServletRequest request) {
		
		//handle image validation for new products
		if(mProduct.getId() == 0) {
		new ProductValidator().validate(mProduct,results);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct,results);
			}
		}
		
		//check if there are any errors
		if(results.hasErrors()) {
				
			model.addAttribute("userClickManageProducts",true);
			
			model.addAttribute("title","Manage Products");
			
			model.addAttribute("message","Validation failed for Product Submission!");
			
			return  "/views/index.jsp";
			
		}
		
		logger.info(mProduct.toString());
		
		//create a new product record
		productRepository.save(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	
	@PostMapping(value = "/product/{id}/activation")
	public String managePostProductActivation(@PathVariable int id) {	
		// is going to fetch the product from the database
		Product product = productService.get(id);
		int isActive = product.getActive();
		//activating and deactivating based on the value of active field
		if(isActive==1)
		{
			product.setActive(0);
			productRepository.save(product);
		
		}
		if(isActive==0) {
			product.setActive(1);
			productRepository.save(product);
		}
		
		return (isActive==0)?"Successfully Deactivated":"Successfully Activated";
	}
	
	//to handle category submission
	@PostMapping(value = "/category")
	public String handleCategorySubmission(@ModelAttribute("category") Category category) {					
		categoryRepository.save(category);	
		return "redirect:/manage/products?operation=category";
	}
	
	//returning categories for all the request mapping
	@ModelAttribute("categories") 
	public List<Category> getCategories() {
		return categoryService.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
