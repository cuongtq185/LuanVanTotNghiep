package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.ImportProductPrice;

public interface ImportProductPriceService {

	public int countAllImportProductPrice();

	public List<ImportProductPrice> findImportProductPricePageable(int limit, int offset);
	
	public void insertPrice(int id, float price);
}
