package vn.com.unit.controller.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.ImportProductCard;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.ImportProductCardService;

@Controller
public class ImportProductCardController {
	@Autowired
	private ImportProductCardService importProductCardService;

	@GetMapping("/admin/imp-card/list")
	public ModelAndView product(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			HttpServletRequest request) {

		int totalitems = importProductCardService.countAllImportProductCard();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<ImportProductCard> pageable = new PageRequest<ImportProductCard>(page, limit, totalitems,
				totalpages);
		List<ImportProductCard> impCard = importProductCardService
				.findImportProductCardPageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(impCard);

		return new ModelAndView("import-card-list");
	}

}
