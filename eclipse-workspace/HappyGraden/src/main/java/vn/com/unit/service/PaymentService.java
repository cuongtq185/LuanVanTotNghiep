package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.BillItem;

public interface PaymentService {

	// Create bill with address
	// Move all product in cart to bill_item
	// Return id bill
	public Long createBill(String address);
	
	public Long calculateBillTotal(Long bill_id);

	public void savePaymentSuccess(Long bill_id);

	public void savePaymentError(Long bill_id);
	
	public void getListQuantityPayment(Long bill_id);
	
}
