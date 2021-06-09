package vn.com.unit.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.Account;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.BillItemService;
import vn.com.unit.service.BillService;
import vn.com.unit.service.ProductService;
import vn.com.unit.service.RoleService;
import vn.com.unit.service.ShopService;

@Controller
public class ProfileController {
	
	@Autowired
	AccountService accountService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	ShopService shopService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	BillService billService;
	
	@Autowired
	BillItemService billItemService;

	
	// home view
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/profile/home")
	public ModelAndView home(Model model) {
		model.addAttribute("title", "Account Management");
		return new ModelAndView("profile/profile");
	}
	
	// create shop view
//	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_VENDOR')")
//	@GetMapping("/profile/createshop")
//	public ModelAndView createShop(Model model) {
//		Account account = accountService.findCurrentAccount();
//		
//		
//		model.addAttribute("title", "Account Management");
//		return new ModelAndView("profile/shop/wait-confirm-shop");
//	}
//	
	
	//editAccount view
	//@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	@GetMapping("/profile/myaccount")
	public ModelAndView myAccount(Model model) {
		
		Account account = accountService.findCurrentAccount();	
		model.addAttribute("account", account);

		return new ModelAndView("account-edit"); 
		}

}
