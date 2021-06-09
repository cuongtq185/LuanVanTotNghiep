package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.Bill;

public interface AdminBillService {

	public int countAllBill();

	public List<Bill> findAllBill(int limit, int offset);
	
	public List<Bill> findAllBillByAccount(Long id);
}
