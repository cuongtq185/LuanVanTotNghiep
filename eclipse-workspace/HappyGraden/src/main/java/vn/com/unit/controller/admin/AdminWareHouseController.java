package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Category;
import vn.com.unit.entity.Product;
import vn.com.unit.entity.WareHouse;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.ProductService;
import vn.com.unit.service.WareHouseService;

@Controller
public class AdminWareHouseController {
	
	@Autowired
	private WareHouseService houseService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/admin/warehouse/list")
	public ModelAndView warehouseList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  houseService.countAllWarehouse();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<WareHouse> pageable = new PageRequest<WareHouse>(page, limit, totalitems, totalpages);
		List<WareHouse> warehouses = houseService.findWareHousePageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(warehouses);
		return new ModelAndView("warehouse");
	}
	
	
	
	@GetMapping("/admin/warehouse/ajax-list")
	public ModelAndView warehouseAjaxList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  houseService.countAllWarehouse();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<WareHouse> pageable = new PageRequest<WareHouse>(page, limit, totalitems, totalpages);
		List<WareHouse> warehouses = houseService.findWareHousePageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(warehouses);
		return new ModelAndView("warehouse");
	}
	
	@GetMapping("/admin/warehouse/edit/{product_id}")
	public ModelAndView impProduct(@PathVariable("product_id") long product_id, Model model,
			HttpServletRequest request) {
		Product product = productService.findProductByProductId(product_id);
		model.addAttribute("product",product);
		return new ModelAndView("import-product-card");
	}
	
	@PutMapping("/admin/warehouse/edit")
	@ResponseBody
	public ResponseEntity<String> impProduct(@RequestBody ProductDto product, Model model) {
		
		houseService.updateProductById(product);

		return ResponseEntity.ok("{ \"msg\" : \"import product successfully.\" }");
	}

}
