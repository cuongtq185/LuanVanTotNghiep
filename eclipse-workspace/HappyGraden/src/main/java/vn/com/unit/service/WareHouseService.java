package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.ImportProductCard;
import vn.com.unit.entity.ImportProductPrice;
import vn.com.unit.entity.WareHouse;

public interface WareHouseService {
	
	public int countAllWarehouse();
	
	public List<WareHouse> findWareHousePageable(int limit,int offset);
	
	public void updateProductById(ImportProductCard importProductCard);
	
	public void insert(int id, int quantity);	
	
	public void insertProductPrice(ImportProductPrice imp);
	
	public void updateAfterPayment(int product, int quantity);
	
	public void updateQuantityProduct(int id, int quantity);

}
