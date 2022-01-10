package com.shopping.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shopping.demo.entities.Category;
import com.shopping.demo.entities.Product;
import com.shopping.demo.exception.ProductNotFoundException;
import com.shopping.demo.repository.ICategoryRepository;
import com.shopping.demo.repository.IProductRepository;
import com.shopping.demo.service.ICategoryService;
import com.shopping.demo.service.IProductService;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	ICategoryService categoryService;

	@Autowired
	IProductService productService;

	@Autowired
	IProductRepository productRepository;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/views/index.jsp");
		mv.addObject("title", "Home");

		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");

		// passing the list of categories
		mv.addObject("categories", categoryService.list());
		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("/views/index.jsp");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("/views/index.jsp");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}

	// Methods to load all the products and based on category
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("/views/index.jsp");
		mv.addObject("title", "All Products");
		// passing the list of categories
		mv.addObject("categories", categoryService.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		try {
			ModelAndView mv = new ModelAndView("/views/index.jsp");

			// fetch a single category;

			Category category = null;
			category = categoryService.get(id);

			mv.addObject("title", category.getName());

			// passing the list of categories
			mv.addObject("categories", categoryService.list());

			// passing the single category object
			mv.addObject("category", category);
			mv.addObject("userClickCategoryProducts", true);
			return mv;
		} catch (Exception exception) {
			throw new ProductNotFoundException();
		}
	}

	// Viewing a single product

	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("/views/index.jsp");
		Product product = productService.get(id);
		// update the view count

		product.setViews(product.getViews() + 1);
		productRepository.save(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);

		mv.addObject("userClickShowProduct", true);

		return mv;
	}
	
	
	

}
