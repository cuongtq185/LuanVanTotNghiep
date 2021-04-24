package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.BillItem;
import vn.com.unit.repository.PaymentRepository;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.BillService;
import vn.com.unit.service.PaymentService;
import vn.com.unit.service.WareHouseService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	AccountService accountService;

	// Order
	@Autowired
	BillService billService;
	
	@Autowired
	WareHouseService wareHouseService;
	
	@Autowired
	PaymentRepository paymentRepository;

	// Get current account id
	// -- Check cart to create bill
	// Create bill (account id, address)
	// Move all product in cart to bill item
	@Override
	public Long createBill(String address, String phone, String fullname) {

		Account current_account = accountService.findCurrentAccount();
		Long current_account_id = current_account.getAccountId();
		
		// Need check cart before create bill
		try {
			billService.createBill(current_account_id, address, phone, fullname);	
		} catch (Exception e) {
			// TODO: handle exception
		}		
		Long bill_id = billService.getIdPayment();
		
		//create payment status 0
		paymentRepository.createPaymentByBill(bill_id);
		
		billService.addBillItemFromCart(bill_id, current_account_id);

		return bill_id;
	}

	@Override
	public Long calculateBillTotal(Long bill_id) {

		return billService.calculateBillTotal(bill_id);
	}

	@Override
	public void savePaymentSuccess(Long bill_id) {
		// TODO Auto-generated method stub
		billService.saveBillPaymentStatus(bill_id, 1);
	}

	@Override
	public void savePaymentError(Long bill_id) {
		// TODO Auto-generated method stub
		billService.saveBillPaymentStatus(bill_id, -1);
	}
	
	@Override
	public void getListQuantityPayment(Long bill_id){
		
		List<BillItem> lst = billService.getListQuantityPayment(bill_id);
		for(BillItem dto: lst) {
			wareHouseService.updateAfterPayment(dto.getProduct(), dto.getQuantity());
		}
	}

	
	
}
