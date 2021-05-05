package vn.com.unit.controller.shop;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
	
	private static final Charset UTF_8 = Charset.forName("UTF-8");
	private static final Charset ISO = Charset.forName("ISO-8859-1");
	
	
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
	
	@GetMapping("/admin/product/edit")
	@ResponseBody
	public ModelAndView productList2(Model model,
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request) {
	
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		List<Origin> origins = originService.findAllOrigin();
		model.addAttribute("origins",origins);
		ProductDto product = productService.getProductById(id);
		model.addAttribute("product",product);
		return new ModelAndView("product-edit");
	}
	
	
	//add product
	//@PostMapping("admin/product/edit")
	@RequestMapping(value = "admin/product/edit", produces = "text/plain; charset=utf-8", method = RequestMethod.POST)
	public ModelAndView addProduct(@Valid @RequestParam(required = false) MultipartFile[] file, RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "productName") String productName,
			@RequestParam(value = "category") int category,
			@RequestParam(value = "origin") int origin,
			@RequestParam(value = "productDetail") String productDetail,
			@RequestParam(value = "productQuantity") int productQuantity,
			@RequestParam(value = "productId") int productId,
			HttpServletRequest request) {
		
		//ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(productName);
		String name = new String(productName.getBytes(ISO),UTF_8);
		String detail = new String(productDetail.getBytes(ISO),UTF_8);
					
		productService.updateProduct(name, category, origin, detail, productId);
		
		ModelAndView mav = new ModelAndView("product-edit");
				
		//Product dto = productService.getIdProductAddNew(name, category, origin);
		//int id = productService.getIdNew();
		if (productQuantity > 0) {
			wareHouseService.updateQuantityProduct(productId, productQuantity);
		}		
		if ( productId != 0) {
			int i = 1;
			for (MultipartFile img : file) {
				 if (file != null && file.length > 0) {
					try {
						name = name.replace("", "_");
						String imgName = productId + name + "_" + String.valueOf(i) + ".jpg";

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
		}
		String url = "admin/product/edit";
		String viewName = "redirect:".concat("/admin/product/edit");
		redirectAttributes.addAttribute("id",productId);
		mav.setViewName(viewName);
		
		MessageList messageList = new MessageList("success");	
//		String[] args = new String[1];
//		String msgInfo = msg.getMessage("post.office.submit.success", args, locale);
//		mav.addObject("msgInfo", msgInfo);

		return mav;
		
	}
	
	@GetMapping("/admin/product/add")
	@ResponseBody
	public ModelAndView addProduct(Model model,
			HttpServletRequest request) {
	
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		List<Origin> origins = originService.findAllOrigin();
		model.addAttribute("origins",origins);
		return new ModelAndView("product-add");
	}
	
	@RequestMapping(value = "admin/product/add", produces = "text/plain; charset=utf-8", method = RequestMethod.POST)
	public ModelAndView addProduct2(@Valid @RequestParam(required = false) MultipartFile[] file,
			RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "productName") String productName, @RequestParam(value = "category") int category,
			@RequestParam(value = "origin") int origin, @RequestParam(value = "productDetail") String productDetail,
			@RequestParam(value = "productQuantity") int productQuantity,
			HttpServletRequest request) {

		// ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(productName);
		String name = new String(productName.getBytes(ISO), UTF_8);
		String detail = new String(productDetail.getBytes(ISO), UTF_8);

		productService.createNewProduct(name, category, origin, detail);
		int id = productService.getIdNew();					
		wareHouseService.insert(id, productQuantity);
		if (id != 0) {
			int i = 1;
			for (MultipartFile img : file) {
				if (file != null && file.length > 0) {
					try {
						name = name.replace("", "_");
						String imgName = id + name + "_" + String.valueOf(i) + ".jpg";

						String path = "D:/LuanVanTotNghiep2021/LuanVanTotNghiep/eclipse-workspace/HappyGraden/src/main/webapp/static/img";
						FileUtil.createDirectoryNotExists(path);
						File fileNew = new File(path, imgName);

						String url = "/static/img/" + imgName;
						img.transferTo(fileNew);
						productImg2DService.saveImg2D(id, url);
						i++;

					} catch (Exception e) {
						e.printStackTrace();
						// Logger.error("fail");
						throw new Error();
					}
				}
			}
		}
		ModelAndView mav = new ModelAndView("product-add");
		String url = "admin/product/edit";
		String viewName = "redirect:".concat("/admin/product/edit");
		redirectAttributes.addAttribute("id", id);
		mav.setViewName(viewName);

		MessageList messageList = new MessageList("success");

		return mav;

	}


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
