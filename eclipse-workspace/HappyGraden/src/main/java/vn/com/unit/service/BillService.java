package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.Bill;

public interface BillService {
	
	public List<Bill> findAllBillByAccountId(Long account_id);
	
	public Bill findBillByBillId(Long bill_id);

	public void createBill(Long account_id, String address);
	
	public Long getIdPayment();

	public void addBillItemFromCart(Long bill_id, Long account_id);

	public Long calculateBillTotal(Long bill_id);

	public void saveBillPaymentStatus(Long bill_id, int i);

	public Bill findBillOfCurrentAccountByBillId(Long id);
	
	public Bill test(Long bill_id);

}
