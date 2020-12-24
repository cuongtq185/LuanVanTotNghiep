package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Category;
import vn.com.unit.entity.ImportProductCard;
import vn.com.unit.entity.Product;
import vn.com.unit.entity.WareHouse;
import vn.com.unit.repository.WareHouseRepository;
import vn.com.unit.service.WareHouseService;

@Service
@Transactional

public class WareHouseServiceImpl implements WareHouseService {
	
	@Autowired
	WareHouseRepository wareHouseRepository;
	
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
	public void updateProductById(ProductDto product) {
		try {
			ImportProductCard card = new ImportProductCard();
			
			card.setProduct(product.getProductId());		
			
			wareHouseRepository.updateProductById(product.getProductId(), product.getImpDetail(), product.getImpQuantity(), product.getImpProductPrice());
			wareHouseRepository.updateQuantity(product.getProductId(), product.getImpQuantity());
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub

	}
	
	@Override
	public void insert(Long id) {
		wareHouseRepository.insert(id);
	}
}
