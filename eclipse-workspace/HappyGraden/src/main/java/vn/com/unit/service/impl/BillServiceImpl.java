package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.Account;
import vn.com.unit.entity.Bill;
import vn.com.unit.entity.BillItem;
import vn.com.unit.repository.BillRepository;
import vn.com.unit.repository.ProductRepository;
import vn.com.unit.repository.ShopRepository;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.BillService;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	ShopRepository shopRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	AccountService accountService;
	
	
	@Override
	public List<Bill> findAllBillByAccountId(Long account_id) {
		
		return billRepository.findAllBillByAccountId(account_id);
	}
	
	@Override
	public Bill findBillByBillId(Long bill_id) {
		Bill bill = billRepository.findBillByBillId(bill_id);
		return bill;
	}

	@Override
	public void createBill(Long account_id, String address) {
		
		billRepository.createBill(account_id, address);
//		Bill bill_temp = new Bill();
//		bill_temp.setAccount(account_id);
//		bill_temp.setAddress(address);
//		
//		return true;
	}

	@Override
	public void addBillItemFromCart(Long billId, Long currentAccountId) {
		
		billRepository.addBillItemFromCart(billId, currentAccountId);
	}
	
	@Override
	public Long getIdPayment() {
		return billRepository.getIdPayment();
	}

	@Override
	public Long calculateBillTotal(Long bill_id) {
		
		return billRepository.calculateBillTotal(bill_id);
	}

	@Override
	public void saveBillPaymentStatus(Long bill_id, int payment_status) {
		billRepository.saveBillPaymentStatus(bill_id, payment_status);
//		Bill bill_temp = new Bill();
//		bill_temp.setId(bill_id);
//		Bill bill_temp = billRepository.findOne(bill_id);
//		bill_temp.setPayment(payment_status);
 
	}

	@Override
	public Bill findBillOfCurrentAccountByBillId(Long bill_id) {
		
		Account current_account = accountService.findCurrentAccount();
		Long current_account_id = current_account.getAccountId();
		
		return billRepository.findBillByBillIdAndAccountId(bill_id, current_account_id);
	}

	@Override
	public Bill test(Long bill_id) {
		return billRepository.test(bill_id);
	}
	
	@Override
	public List<BillItem> getListQuantityPayment(Long bill_id){		
		List<BillItem> lstBill = billRepository.getListQuantityPayment(bill_id);
		return lstBill;
	}

}
