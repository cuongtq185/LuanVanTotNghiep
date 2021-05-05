package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.ImportProductCard;
import vn.com.unit.entity.ImportProductPrice;
import vn.com.unit.entity.WareHouse;
import vn.com.unit.repository.WareHouseRepository;
import vn.com.unit.service.ProductService;
import vn.com.unit.service.WareHouseService;

@Service
@Transactional

public class WareHouseServiceImpl implements WareHouseService {
	
	@Autowired
	WareHouseRepository wareHouseRepository;
	
	@Autowired
	ProductService productService;
	
	@Override
	public int countAllWarehouse() {
		// TODO Auto-generated method stub
		try {
			return wareHouseRepository.countAllWareHouse();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	@Override
	public List<WareHouse> findWareHousePageable(int limit, int offset) {
		// TODO Auto-generated method stub
		try {
			return wareHouseRepository.findWareHousePageable(limit, offset);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void updateProductById(ImportProductCard product) {
		try {
			
			int id = productService.getIdByProductName(product.getName());
			Long impId = Long.valueOf(id);
			wareHouseRepository.updateProductById(product.getName(), product.getImpDetail(), product.getImpQuantity(), product.getImpProductPrice());
			wareHouseRepository.updateQuantity(impId, product.getImpQuantity());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void insert(int id, int quantity) {
		wareHouseRepository.insert(id, quantity);
	}
	
	@Override
	public void insertProductPrice(ImportProductPrice product) {
		try {
			
			int id = productService.getIdByProductName(product.getProductName());
			//Long impId = Long.valueOf(id);		
			wareHouseRepository.insertPrice(id, product.getProductPrice());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void updateAfterPayment(int product, int quantity) {
		wareHouseRepository.updateAfterPayment(product, quantity);
	}
	
	@Override
	public void updateQuantityProduct(int id, int quantity) {
		try {
			wareHouseRepository.updateQuantityProduct(id, quantity);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
