package vn.com.unit.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.ImportProductPrice;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.ImportProductPriceService;

@Controller
public class ImportProductPriceController {

	@Autowired
	private ImportProductPriceService importProductPriceService;

	@GetMapping("/admin/product-price/list")
	public ModelAndView product(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			HttpServletRequest request) {

		int totalitems = importProductPriceService.countAllImportProductPrice();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<ImportProductPrice> pageable = new PageRequest<ImportProductPrice>(page, limit, totalitems,
				totalpages);
		List<ImportProductPrice> impPrice= importProductPriceService
				.findImportProductPricePageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(impPrice);

		return new ModelAndView("price-list");
	}
}
