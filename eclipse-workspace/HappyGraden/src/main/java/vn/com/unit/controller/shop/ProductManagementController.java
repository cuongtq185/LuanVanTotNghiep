package vn.com.unit.controller.shop;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Category;
import vn.com.unit.entity.Origin;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.BillItemService;
import vn.com.unit.service.BillSeparateService;
import vn.com.unit.service.CategoryService;
import vn.com.unit.service.OriginService;
import vn.com.unit.service.ProductImg2DService;
import vn.com.unit.service.ProductService;
import vn.com.unit.service.RoleService;
import vn.com.unit.service.WareHouseService;
import vn.com.unit.utils.FileUtil;
import vn.com.unit.utils.MessageList;

@Controller
public class ProductManagementController {

	@Autowired
	AccountService accountService;

	@Autowired
	RoleService roleService;

	@Autowired
	ProductService productService;

//	@Autowired
//	BillService billService;

	@Autowired
	BillItemService billItemService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	OriginService originService;
	
	@Autowired
	WareHouseService wareHouseService;

	@Autowired
	BillSeparateService billSeparateService;
	
	@Autowired
	ProductImg2DService productImg2DService; 
	
	@Autowired
	protected MessageSource msg;
	
	
	@GetMapping("/admin/product/edit/{product_id}")
	@ResponseBody
	public ModelAndView productList(Model model,
			 @PathVariable("product_id") Long product_id,
			HttpServletRequest request) {
	
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		List<Origin> origins = originService.findAllOrigin();
		model.addAttribute("origins",origins);
		ProductDto product = productService.getProductById(product_id);
		model.addAttribute("product",product);
		return new ModelAndView("product-edit");
	}
	
	@GetMapping("/admin/product/edit?id={product_id}")
	@ResponseBody
	public ModelAndView productList2(Model model,
			 @PathVariable("product_id") Long product_id,
			HttpServletRequest request) {
	
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		List<Origin> origins = originService.findAllOrigin();
		model.addAttribute("origins",origins);
		ProductDto product = productService.getProductById(product_id);
		model.addAttribute("product",product);
		return new ModelAndView("product-edit");
	}
	
	
	//add product
	//@PostMapping("admin/product/edit")
	@RequestMapping(value = "admin/product/edit", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public ModelAndView addProduct(@RequestParam("file") MultipartFile[] file, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "productName") String productName,
			@RequestParam(value = "category") int category,
			@RequestParam(value = "origin") int origin,
			@RequestParam(value = "productDetail") String productDetail,
			@RequestParam(value = "productQuantity") int productQuantity,
			@RequestParam(value = "productId") int productId,
			HttpServletRequest request) {
		
		MessageList messageList = new MessageList("success");	
		productService.updateProduct(productName, category, origin, productDetail, productId);
		
		ModelAndView mav = new ModelAndView("product-edit");
				
		//Product dto = productService.getIdProductAddNew(name, category, origin);
		//int id = productService.getIdNew();
		if (productQuantity > 0) {
			wareHouseService.updateQuantityProduct(productId, productQuantity);
		}
		if ( productId != 0) {
			int i = 1;
			for (MultipartFile img : file) {
				try {
					productName = productName.replace("", "_");
					String imgName = productId + productName + "_" + String.valueOf(i) + ".jpg";

					String path = "D:/LuanVanTotNghiep2021/LuanVanTotNghiep/eclipse-workspace/HappyGraden/src/main/webapp/static/img";
					FileUtil.createDirectoryNotExists(path);
					File fileNew = new File(path, imgName);

					String url = "/static/img/" + imgName;
					img.transferTo(fileNew);
					productImg2DService.updateImg2D(productId, url);
					i++;

				} catch (Exception e) {
					e.printStackTrace();
					// Logger.error("fail");
					throw new Error();
				}
			}
		}
		String url = "admin/product/edit";
		String viewName = "redirect:".concat("/admin/product/edit");
		redirectAttributes.addAttribute(productId);
		mav.setViewName(viewName);
//		String[] args = new String[1];
//		String msgInfo = msg.getMessage("post.office.submit.success", args, locale);
//		mav.addObject("msgInfo", msgInfo);

		return mav;
		
	}

	// edit Product
//	@PreAuthorize("hasRole('ROLE_VENDOR')")
//	@PutMapping("/product/{product_id}")
//	@ResponseBody
//	public ResponseEntity<Product> editShop(@RequestBody Product product, @PathVariable("product_id") Long product_id,
//			Model model) {
//		
//		Product product_old = productService.findOne(product_id);
//		
//		Account account_current = accountService.findCurrentAccount();
//		
//		if (product_old.getShop() != account_current.getId()) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(product);
//		}
//		
//		String name = product.getName();
//		int category = product.getCategory();
//		int brand = product.getBrand();
//		int price = product.getPrice();
//		int quantity = product.getQuantity();
//		String detail = product.getDetail();
////		productService.saveProduct(product_id, name, price, detail, category, brand, quantity);
//
////		product.setShop(null);
////		
////		product.setId(product_id);
//		
//		product_old.setName(name);
//		product_old.setCategory(category);
//		product_old.setBrand(brand);
//		product_old.setPrice(price);
//		product_old.setQuantity(quantity);
//		product_old.setDetail(detail);
//		
//		Product product_new = productService.save(product_old);
//		
//		return ResponseEntity.ok(product_new);
//
//	}

	// deleteProduct
	@DeleteMapping("/admin/product/delete/{product_id}")
	public ResponseEntity<Void> deleteProduct(HttpServletRequest request,@PathVariable("product_id") Long product_id) {		
		productService.setDisableProductByProductId(product_id);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	// confirm bill
	@PreAuthorize("hasRole('ROLE_VENDOR')")
	@PutMapping("/bill/confirm")
	@ResponseBody
	public ResponseEntity<String> confirm(Model model, @RequestBody Map<String, String> json) {
		int status = 1;

		billSeparateService.saveBillSeparateStatus((Long.valueOf(json.get("bill_id"))), status);

		return ResponseEntity.ok("{\"msg\" : \"Confirm Bill succes! Please check again!\" }");
	}

	// confirm payment bill
	@PreAuthorize("hasRole('ROLE_VENDOR')")
	@PutMapping("/bill/payment")
	@ResponseBody
	public ResponseEntity<String> payment(Model model, @RequestBody Map<String, String> json) {
		int status = 1;

//		billService.saveBillPaymentStatus((Long.valueOf(json.get("bill_id"))), status);

		return ResponseEntity.ok("{\"msg\" : \"Confirm Bill Payment succes! Please check again!\" }");
	}

	// deny bill
	@PreAuthorize("hasRole('ROLE_VENDOR')")
	@PutMapping("/bill/deny")
	@ResponseBody
	public ResponseEntity<String> deny(Model model, @RequestBody Map<String, String> json) {
		int status = 2;

		billSeparateService.saveBillSeparateStatus((Long.valueOf(json.get("bill_id"))), status);

		return ResponseEntity.ok("{\"msg\" : \"Deny bill succes! Please check again!\" }");
	}
	
}
