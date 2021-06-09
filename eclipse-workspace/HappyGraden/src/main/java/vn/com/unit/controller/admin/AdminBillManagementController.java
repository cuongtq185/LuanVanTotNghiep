package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.Bill;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.AdminBillService;
import vn.com.unit.service.PaymentService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminBillManagementController {

	@Autowired
	private AdminBillService adminBillService;
	
	@Autowired
	PaymentService paymentService;

	@GetMapping("/admin/bill/list")
	public ModelAndView product(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			HttpServletRequest request) {

		int totalitems = adminBillService.countAllBill();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<Bill> pageable = new PageRequest<Bill>(page, limit, totalitems,
				totalpages);
		List<Bill> bill = adminBillService.findAllBill(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(bill);

		return new ModelAndView("admin-bill");
	}
}
