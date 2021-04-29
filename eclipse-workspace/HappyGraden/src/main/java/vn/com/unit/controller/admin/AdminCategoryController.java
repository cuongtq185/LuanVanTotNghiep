package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.Category;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.CategoryService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminCategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/admin/category/list")
	public ModelAndView accountList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  categoryService.countAllCategory();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<Category> pageable = new PageRequest<Category>(page, limit, totalitems, totalpages);
		List<Category> categories = categoryService.findCategoryPageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(categories);
		return new ModelAndView("category");
	}
	
	
	@GetMapping("/admin/category/ajax-list")
	public ModelAndView accountAjaxList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  categoryService.countAllCategory();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<Category> pageable = new PageRequest<Category>(page, limit, totalitems, totalpages);
		List<Category> categories = categoryService.findCategoryPageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(categories);
		return new ModelAndView("category");
	}
	
	
	
	@GetMapping("/admin/category/add")
	public ModelAndView categoryAdd(Model model,
			HttpServletRequest request) {

		return new ModelAndView("category-add");
	}
		
	
	@GetMapping("/admin/category/edit/{category_id}")
	public ModelAndView categoryEdit(@PathVariable("category_id") long category_id, Model model,
			HttpServletRequest request) {
		Category category = categoryService.findCategoryById(category_id);
		model.addAttribute("category",category);
		return new ModelAndView("category-edit");
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@PostMapping("/admin/category/add")
	@ResponseBody
	public ResponseEntity<String> createCategory(@RequestBody Category category, Model model) {
		Category categoryExits = categoryService.findCategoryByName(category.getCategoryName());
		if (categoryExits != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Loại cây cảnh đã tồn tại trong hệ thống!\" }");			
			}
		if (category.getCategoryName() == "" || category.getCategoryName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Tên loại cây cảnh không được để trống!\" }");
		}
		categoryService.createCategory(category);		
		return ResponseEntity.ok("{\"msg\" : \"Thêm thành công\" }");
		
	}
	@PutMapping("/admin/category/edit")
	@ResponseBody
	public ResponseEntity<String> editCategory(@RequestBody Category category, Model model) {
		
		
		if (categoryService.findCategoryByName(category.getCategoryName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Loại cây cảnh đã tồn tại trong hệ thống.\" }");}
		
		if (category.getCategoryName() == null || category.getCategoryName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Tên loại cây cảnh không được để trống.\" }");
		}
		categoryService.updateCategoryById(category);

		return ResponseEntity.ok("{ \"msg\" : \"Cập nhật thành công.\" }");
	}
	@DeleteMapping("/admin/category/delete/{category_id}")
	public ResponseEntity<Boolean> AdminDisableCategory(Model model, @PathVariable("category_id") Long category_id,
			HttpServletRequest request) {
		categoryService.deleteCategoryById(category_id);
		return  ResponseEntity.ok(null);
	}
		
	
}
