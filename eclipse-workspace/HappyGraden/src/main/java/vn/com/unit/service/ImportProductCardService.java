package vn.com.unit.service;

import java.util.List;

import vn.com.unit.entity.ImportProductCard;

public interface ImportProductCardService {

	public int countAllImportProductCard();

	public List<ImportProductCard> findImportProductCardPageable(int limit, int offset);
	
	public void importProductNew(ImportProductCard imp);

}
