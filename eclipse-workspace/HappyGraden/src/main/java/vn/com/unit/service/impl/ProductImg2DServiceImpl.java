package vn.com.unit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.repository.ProductImg2DRepository;
import vn.com.unit.service.ProductImg2DService;

@Service
@Transactional
public class ProductImg2DServiceImpl implements ProductImg2DService{
	
	@Autowired
	ProductImg2DRepository productImg2DRepository;
	
	@Override
	public void saveImg2D(int id, String url) {
		try {
			productImg2DRepository.saveImg2D(id, url);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void updateImg2D(int id, String url) {
		try {
			productImg2DRepository.updateImg2D(id, url);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
