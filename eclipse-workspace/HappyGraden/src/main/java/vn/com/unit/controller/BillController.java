package vn.com.unit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.dto.BillItemDto;
import vn.com.unit.dto.CartDto;
import vn.com.unit.entity.Account;
import vn.com.unit.entity.Bill;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.AdminBillService;
import vn.com.unit.service.BillItemService;
import vn.com.unit.service.BillService;
import vn.com.unit.service.CartService;
import vn.com.unit.service.CategoryService;
import vn.com.unit.utils.CommonUtils;

@Controller
public class BillController {

	@Autowired
	BillService billService;
	
	@Autowired
	BillItemService billItemService;
	
	@Autowired
	CartService cartService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AdminBillService adminBillService;
	
	//@GetMapping("/bill/{id}")
	@RequestMapping(value = "/bill/{id}", produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
	public ModelAndView bill(@PathVariable ("id") Long id, Model model) {
		
		Bill bill = billService.findBillOfCurrentAccountByBillId(id);
		
		if (bill == null) {
			return new ModelAndView("/404");
		}
		
		model.addAttribute("bill", bill);
		
		Long total = billService.calculateBillTotal(id);
		
		model.addAttribute("total", total);
		
		List<BillItemDto> bill_item = billItemService.findAllBillItemByBillId(id);
		
		model.addAttribute("bill_item", bill_item);
		
		return new ModelAndView("bill");
	}
	
	@RequestMapping(value = "/bill-information", produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
	public ModelAndView billInformation(Model model) {
		
		int total_cart_item= 0;
		Long total = 0L;
		Account account = accountService.findCurrentAccount();
		total_cart_item = cartService.countAllCartItemByCurrentAccount(account.getAccountId());
		model.addAttribute("total_cart_item", total_cart_item);
	
		model.addAllAttributes(CommonUtils.getMapHeaderAtribute(model, categoryService));

		List<CartDto> list_cart_item = cartService.findAllCartItemByCurrentAccount();

		model.addAttribute("list_cart_item", list_cart_item);

		total = cartService.calculateCartTotalByCurrentAccount();

		model.addAttribute("total", total);
		model.addAttribute("total_price", total);
		
		List<Bill> list = adminBillService.findAllBillByAccount(account.getAccountId());
		model.addAttribute("list", list);
		

		return new ModelAndView("bill-information");
	}
	
}
