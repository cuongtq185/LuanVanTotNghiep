package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.Category;
import vn.com.unit.entity.ImportProductCard;
import vn.com.unit.entity.ImportProductPrice;
import vn.com.unit.entity.Origin;
import vn.com.unit.entity.Product;
import vn.com.unit.entity.WareHouse;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.CategoryService;
import vn.com.unit.service.ImportProductCardService;
import vn.com.unit.service.ImportProductPriceService;
import vn.com.unit.service.OriginService;
import vn.com.unit.service.ProductImg2DService;
import vn.com.unit.service.ProductService;
import vn.com.unit.service.WareHouseService;

@Controller
public class AdminWareHouseController {
	
	@Autowired
	private WareHouseService houseService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ImportProductCardService importProductCardService;
	
	@Autowired
	OriginService originService;
	
	@Autowired
	ProductImg2DService productImg2DService; 
	
	@Autowired
	ImportProductPriceService importProductPriceService;
	
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
		Product warehouse = productService.findProductByProductId(product_id);
		model.addAttribute("warehouse",warehouse);
		return new ModelAndView("import-product-card");
	}
	
	@RequestMapping(value = "/admin/warehouse/edit", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	//@PutMapping("/admin/warehouse/edit")
	@ResponseBody
	public ResponseEntity<String> impProduct(@RequestBody ImportProductCard imp, Model model) {
		
//		if (imp.getImpProductPrice() <= 0.0) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Price must be less than 0.0\" }");
//		}
		
		String quantity =String.valueOf(imp.getImpQuantity());  
		if (quantity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Số lượng sản phẩm không được để trống\" }");
		}
		if (imp.getImpQuantity() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Số lượng cây cảnh nhập phải lớn hơn 0\" }");
		}

		houseService.updateProductById(imp);
		return ResponseEntity.ok("{ \"msg\" : \"Nhập số lượng sản phẩm thành công.\" }");
	}
	
	@GetMapping("/admin/price/edit/{product_id}")
	public ModelAndView impProductPrice(@PathVariable("product_id") long product_id, Model model,
			HttpServletRequest request) {
		Product warehouse = productService.findProductByProductId(product_id);
		model.addAttribute("warehouse", warehouse);
		return new ModelAndView("import-product-price");
	}
	
	@RequestMapping(value = "/admin/price/edit", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	//@PutMapping("/admin/price/edit")
	@ResponseBody
	public ResponseEntity<String> impProductPrice(@RequestBody ImportProductPrice imp, Model model) {
		
		String a =String.valueOf(imp.getProductPrice());  
		if (a.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Giá nhập sản phẩm không được để trống\" }");
		}
		if (imp.getProductPrice() <= 0.0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Giá nhập sản phẩm không được bé hơn hoặc bằng 0\" }");
		}
		
		houseService.insertProductPrice(imp);
		return ResponseEntity.ok("{ \"msg\" : \"Cập nhật giá thành công.\" }");
	}
	
	/*Nhập hàng*/
	@GetMapping("/admin/wh/import")
	@ResponseBody
	public ModelAndView impProductNew(Model model,
			HttpServletRequest request) {
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		List<Origin> origins = originService.findAllOrigin();
		model.addAttribute("origins",origins);
		return new ModelAndView("import-product");
	}
	
	@RequestMapping(value = "/admin/warehouse/imp", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> impProductNew(@RequestBody ImportProductCard imp, Model model) {
		
		if(imp.getName() == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Tên cây cảnh không được để trống\" }");
		}
		try {
			Product name = productService.getProductByName(imp.getName());
			if(!name.getProductName().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Tên cây cảnh đã tồn tại trong hệ thống\" }");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(imp.getCategory() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Loại cây cảnh không được để trống\" }");
		}
		if(imp.getOrigin() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Xuất xứ cây cảnh không được để trống\" }");
		}
		String quantity =String.valueOf(imp.getImpQuantity());  
		if (quantity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Số lượng sản phẩm không được để trống\" }");
		}
		if (imp.getImpQuantity() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Số lượng cây cảnh nhập phải lớn hơn 0\" }");
		}
		String a =String.valueOf(imp.getImpProductPrice());  
		if (a.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Giá nhập sản phẩm không được để trống\" }");
		}
		if (imp.getImpProductPrice() <= 0.0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Giá nhập sản phẩm không được bé hơn hoặc bằng 0\" }");
		}
		String s =String.valueOf(imp.getPrice());  
		if (s.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Giá bán sản phẩm không được để trống\" }");
		}
		if (imp.getPrice() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Giá bán sản phẩm không được bé hơn hoặc bằng 0\" }");
		}
		importProductCardService.importProductNew(imp);
		productService.createNewProduct(imp.getName(), imp.getCategory(), imp.getOrigin(), null);
		int id = productService.getIdNew();
		houseService.insert(id, imp.getImpQuantity());
		productImg2DService.saveImg2D( id, null);
		try {
			importProductPriceService.insertPrice(id, imp.getPrice());
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return ResponseEntity.ok("{ \"msg\" : \"Nhập sản phẩm thành công.\" }");
	}

}
