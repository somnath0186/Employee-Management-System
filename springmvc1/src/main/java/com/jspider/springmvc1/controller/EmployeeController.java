package com.jspider.springmvc1.controller;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jspider.springmvc1.pojo.AdminPOJO;
import com.jspider.springmvc1.pojo.EmployeePojo;
import com.jspider.springmvc1.repository.EmployeeRepository;
import com.jspider.springmvc1.services.AdminService;
import com.jspider.springmvc1.services.EmpolyeeServices;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class EmployeeController {
	
	@Autowired
	EmpolyeeServices service;
	
	@Autowired
	AdminService adminService;
	//Home controller
	@GetMapping("/home")
	public String home(@SessionAttribute (name="login" ,required= false)AdminPOJO login,ModelMap map) {
	if(login !=null) {
		return "Home";
	}
	
	map.addAttribute("msg","Please Login to proceed");
	return "Login";
	}
	@GetMapping("/add")
	public String addPage(@SessionAttribute (name="login" ,required= false)AdminPOJO login,ModelMap map) {
	if(login !=null) {
		return "Add";
	}
	map.addAttribute("msg", "Please Login to proceed");
	return "Login";
	}
	
	//Add Data Controller
	@PostMapping("/add")
	public String add( @SessionAttribute (name="login" ,required= false)AdminPOJO login,@RequestParam String name,
			           @RequestParam String email,
			           @RequestParam long contact,
			           @RequestParam String designation,
			           @RequestParam double salary,
			           ModelMap map) {
		
		if(login !=null) {
			EmployeePojo employee=service.addEmployee(name, email, contact, designation, salary);
			if(employee !=null) {
	              //Success 
				map.addAttribute("msg","Data Added Successfully...!!!");
			return"Add";
			}else {
				//failure
				map.addAttribute("msg","Data not Added Successfully...!!!");
				return "Add";
			}
		}
		map.addAttribute("msg", "Please Login to proceed");
		return "Login";
	}
 
	//search page controller
	@GetMapping("/search")
	public String searchPage(@SessionAttribute (name="login" ,required= false)AdminPOJO login,ModelMap map){
		if(login !=null) {
			return "Search";
		}
		map.addAttribute("msg", "Please Login to proceed");
		return "Login";
	}
	
	// Search Data Controller
	@PostMapping("/search")
	public String search(@SessionAttribute (name="login" ,required= false)AdminPOJO login,@RequestParam int id, ModelMap map) {
		EmployeePojo employee = service.search(id);
	if(login !=null) {
		if (employee != null) {
			// Success Response
			map.addAttribute("msg", "Employee record found successfully..!!");
			map.addAttribute("emp", employee);
			return "Search";
		}
		// Failure Response
		map.addAttribute("msg", "Employee record not found..!!");
		return "Search";
	}
	map.addAttribute("msg", "Please Login to proceed");
	return "Login";
	}

	// Remove Page Controller
		@GetMapping("/remove")
		public String removePage(@SessionAttribute (name="login" ,required= false)AdminPOJO login,ModelMap map) {
			if(login !=null) {
				List<EmployeePojo> employees = service.searchAllEmployees();
				map.addAttribute("empList", employees);
				return "Remove";
			}
			map.addAttribute("msg", "Please Login to proceed");
			return "Login";
		}
		
		//Remove Data Controller
		@PostMapping("/remove")
		public String remove(@SessionAttribute (name="login" ,required= false)AdminPOJO login,@RequestParam int id, ModelMap map) {
			if(login !=null) {
				EmployeePojo employee = service.search(id);
				if (employee != null) {
					//Success Response
					service.removeEmployee(id);
					List<EmployeePojo> employees = service.searchAllEmployees();
					map.addAttribute("empList", employees);
					map.addAttribute("msg", "Employee removed successfully..!!");
					return "Remove";
				}
				//Failure Response
				List<EmployeePojo> employees = service.searchAllEmployees();
				map.addAttribute("empList", employees);
				map.addAttribute("msg", "Employee data does not exist..!!");
				return "Remove";
			}
			map.addAttribute("msg", "Please Login to proceed");
			return "Login";
		}
     //Update form Page 
		@GetMapping("/update")
		public String updatePage(@SessionAttribute (name="login" ,required= false)AdminPOJO login,ModelMap map) {
			if(login !=null) {
				List<EmployeePojo> employees = service.searchAllEmployees();
				map.addAttribute("empList", employees);
				return "Update";
			}
			map.addAttribute("msg", "Please Login to proceed");
			return "Login";
		}
		//Update form controller
		
		@PostMapping("/update")
		public String updateForm(@SessionAttribute (name="login" ,required= false)AdminPOJO login,@RequestParam int id  ,ModelMap map) {
			
			if(login !=null) {
				EmployeePojo employee=service.search(id);
				
				if(employee !=null) {
					//Sucess Response
					map.addAttribute("emp", employee);
					
					return "Update";
				}
				map.addAttribute("msg", "Data not found...!");
			  	return "Update";
			}
			map.addAttribute("msg", "Please Login to proceed");
			return "Login";
		}
		
		//Update Data controller
		@PostMapping("/updateData")
		public String update(@SessionAttribute (name="login" ,required= false)AdminPOJO login,@RequestParam int id,
				@RequestParam String name,
				             @RequestParam String email,
				             @RequestParam long contact,
				             @RequestParam String designation,
				             @RequestParam double salary,ModelMap map ) {
              
			if(login !=null) {
				EmployeePojo employee=service.search(id);
				if(employee !=null) {
					//success Response
					service.updateEmployee(id,name,email,contact,designation,salary);
			map.addAttribute("msg","Data Update Succesfully....!");
			List<EmployeePojo> employees = service.searchAllEmployees();
			map.addAttribute("empList", employees);
			return "Update";
				}
				//Failure Response
				map.addAttribute("msg", "Data Not updated");
				return "Update";
			}
			map.addAttribute("msg", "Please Login to proceed");
			return "Login";
		}
		
		//Create Admin Page Controller
		
		@GetMapping("/create")
		public String createAdminPage() {
			return "Admin";
		}
		
		//Create Admin Data Controller
		@PostMapping("/create")
		public String createAdmin(@RequestParam String username,
				                  @RequestParam String password,ModelMap map) {
			AdminPOJO admin=adminService.create(username,password);
			if(admin !=null) {
				//success response
				map.addAttribute("msg", "Acount Created Succesfully...!");
				return "Login";
			}
			//Failure Response
			map.addAttribute("msg", "Account not Created ...!" );
			return "Login";
		}
		
		
		//Login Controller
		@GetMapping("/login")
		public String login( @RequestParam String username, @RequestParam String password,
				ModelMap map,HttpServletRequest request) {
			AdminPOJO admin = adminService.login(username, password);
			if (admin != null) {
				// Success response
              HttpSession session= request.getSession();
              session.setAttribute("login", admin);
				return "Home";
			}
			// Failure response
			map.addAttribute("msg", "Invalid username or password");
			return "Login";
		}
	
       //Logout Controller
		@GetMapping("/logout")
		public String logout(ModelMap map,HttpSession session){
			map.addAttribute("msg","Logout Sucessfully...!!");
			session.invalidate();
			return "Login";
			
		}
}
		
       

