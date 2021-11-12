package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the customer service
	@Autowired
	private CustomerService customerService;
	
//	@GetMapping("/list")
//	public String listCustomer(Model theModel) {
//		
//		//get customers from the service
//		List<Customer> theCustomers = customerService.getCustomers();
//		//add the customers to the model
//		theModel.addAttribute("customers", theCustomers);
//		
//		return "list-customers";
//	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		//create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save the customer using our service
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		Customer theCustomer = customerService.getCustomer(theId);
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
		
	}
	
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam String theSearchName, Model theModel) {
		
		List<Customer> theCustomers = customerService.searchCustomer(theSearchName);
		
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/list")
	public String listCustomer(Model theModel, @RequestParam(required=false) String sort) {
		
		List<Customer> theCustomers = null;
		
		if(sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);
		}else {
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
}
