package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.Bill;
import vn.com.unit.repository.AdminBillRepository;
import vn.com.unit.service.AdminBillService;

@Service
@Transactional
public class AdminBillServiceImpl implements AdminBillService {

	@Autowired
	AdminBillRepository adminBillRepository;
	
	@Override
	public int countAllBill() {
		try {
			return adminBillRepository.countAllBill();
			} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Bill> findAllBill(int limit, int offset) {
		try {
			return adminBillRepository.findAllBill(limit, offset);
		} catch (Exception e) {
		}
		return null;
	}
	
	@Override
	public List<Bill> findAllBillByAccount(Long id){
	
		return adminBillRepository.findAllBillByAccount(id);

	}
}
