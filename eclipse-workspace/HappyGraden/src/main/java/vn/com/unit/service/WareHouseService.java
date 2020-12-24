package vn.com.unit.service;

import java.util.List;

import vn.com.unit.dto.ProductDto;
import vn.com.unit.entity.Product;
import vn.com.unit.entity.WareHouse;

public interface WareHouseService {
	
	public int countAllWarehouse();
	
	public List<WareHouse> findWareHousePageable(int limit,int offset);
	
	public void updateProductById(ProductDto product);
	
	public void insert(Long id);

}
