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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	@GetMapping("/admin/product/add")
	@ResponseBody
	public ModelAndView productAdd(Model model, 
			HttpServletRequest request) {
	
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		List<Origin> origins = originService.findAllOrigin();
		model.addAttribute("origins",origins);
		return new ModelAndView("product-add");
	}
	
	
	//add product
	@PostMapping("admin/product/add")
	public ModelAndView addProduct(@RequestParam("file") MultipartFile[] file, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "category") int category,
			@RequestParam(value = "origin") int origin,
			@RequestParam(value = "detail") String detail,
			HttpServletRequest request) {
		
		MessageList messageList = new MessageList("success");	
		productService.createNewProduct(name, category, origin, detail);
		ModelAndView mav = new ModelAndView("product-add");
//		Product product = new Product();
//		product.setProductName(name);
//		product.setCategory(category);
//		product.setOrigin(origin);
//		product.setProductDetail(detail);
				
		//Product dto = productService.getIdProductAddNew(name, category, origin);
		int id = productService.getIdNew();
		//wareHouseService.insert(id);
		
		if ( id != 0) {
			int i = 1;
			for (MultipartFile img : file) {
				try {
					name = name.replace("", "_");
					String imgName = id + name + "_" + String.valueOf(i) + ".jpg";

					String path = "D:/LuanVanTotNghiep2021/LuanVanTotNghiep/eclipse-workspace/HappyGraden/src/main/webapp/static/img";
					FileUtil.createDirectoryNotExists(path);
					File fileNew = new File(path, imgName);

					String url = "/static/img/" + imgName;
					img.transferTo(fileNew);
					productImg2DService.saveImg2D( id, url);
					i++;

				} catch (Exception e) {
					e.printStackTrace();
					// Logger.error("fail");
					throw new Error();
				}
			}
		}
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
	
	
	//list-bill-new
//	@PreAuthorize("hasRole('ROLE_VENDOR')")
//	@PostMapping("/mybills/waiting-confirm-test")
//	@ResponseBody
//	public ResponseEntity<String> test(Model model, 
//			@RequestBody Map<String, String> mode_data, HttpServletResponse response) {
//		
//		int page = Integer.parseInt(mode_data.get("page"));
//		int limit = Integer.parseInt(mode_data.get("limit"));
//		
//		Long status = (long) 0;
//		Long payment = (long) 0;
//		String mode = mode_data.get("mode").toString();
//		if(mode.equals("waitingPayment")) {
//			status = (long) 0;
//			payment = (long) 0;			
//		}
//		if(mode.equals("waitingConfirm")) {
//			status = (long) 0;
//			payment = (long) 1;			
//		}
//		if(mode.equals("confirmList")) {
//			status = (long) 1;
//			payment = (long) 1;			
//		}
//		if(mode.equals("successList")) {
//			status = (long) 3;
//			payment = (long) 1;			
//		}
//		if(mode.equals("denyList")) {
//			status = (long) 2;
//			payment = (long) 1;			
//		}
//		if(mode.equals("errorPayment")) {
//			status = (long) 0;
//			payment = (long) -1;			
//		}
//		
//		Account current_account = accountService.findCurrentAccount();
//		
//		int totalitems = billSeparateService.countBillSeparateByPaymentAndStatusAndShopId(payment, status, current_account.getId());
//		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
//		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);
//		List<BillSeparateShopViewDto> bills = billSeparateService.findBillSeparateByPaymentAndStatusAndShopId(payment, status, current_account.getId(),pageable.getLimit(),pageable.getOffset());
//		for (BillSeparateShopViewDto bill : bills) {
//			int total = billItemService.totalPriceOfBillByBillSeparateId(bill.getId());
//			bill.setTotalPrice(total);
////			try {
////				bill.setAddress(CommonUtils.convertEncode(bill.getAddress()));
////			} catch (UnsupportedEncodingException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//		}
//		model.addAttribute("pageable", pageable);
//		String json = "";
//		String json2 = "";
//		String json3 = "{\"mode\" :\""+mode+"\"}";
//		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//		try {
//			json = ow.writeValueAsString(pageable);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			json2 = ow.writeValueAsString(bills);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<String> s = new ArrayList<String>();
//		s.add(json);
//		s.add(json2);
//		s.add(json3);
//		String data = s.toString();
////		try {
////			data = CommonUtils.convertEncode(data);
////		} catch (UnsupportedEncodingException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		response.setHeader("Content-Type", "application/json; charset=utf-8");
//		
//		return ResponseEntity.ok(data);
//	}
//	
//	
//
//	
//	
	
	
	
	
	
	
}
