package vn.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import vn.com.unit.entity.Account;
import vn.com.unit.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@RequestMapping(value = "/register", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createAccount(@RequestBody Account account, Model model) {
		
		if (account.getAccountName() == null || account.getAccountName().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Họ và tên không được để trống\" }");
		}
		
		if (account.getAccountEmail() == null || account.getAccountEmail().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Email không được để trống\" }");
		}
		
		if (account.getAccountUsername() == null || account.getAccountUsername().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Tên tài khoản không được để trống\" }");
		}

		if (account.getAccountPassword() == null || account.getAccountPassword().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Mật khẩu không được để trống\" }");
		}

		if (account.getAccountAddress() == null || account.getAccountAddress().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Địa chỉ không được để trống\" }");
		}

		if (account.getAccountPhone() == null || account.getAccountPhone().equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Số điện thoại không được để trống\" }");
		}

		if (account.getAccountPassword().length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{ \"msg\" : \"Mật khẩu quá ngắn - Nhập ít nhất 8 kí tự\" }");
		}

		if (accountService.findByUsername(account.getAccountUsername()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Tên tài khoản đã tồn tại\" }");
		}

		accountService.createNewAccount(account);		
		return ResponseEntity.ok("{\"msg\" : \"Tạo tài khoản thành công\" }");
	}

}
