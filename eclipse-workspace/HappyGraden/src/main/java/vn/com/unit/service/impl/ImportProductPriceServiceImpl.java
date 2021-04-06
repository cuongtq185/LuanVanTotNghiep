package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.ImportProductPrice;
import vn.com.unit.repository.ImportProductPriceRepository;
import vn.com.unit.service.ImportProductPriceService;
import vn.com.unit.service.ProductService;

@Service
@Transactional
public class ImportProductPriceServiceImpl implements ImportProductPriceService{
	
	@Autowired
	ImportProductPriceRepository importProductPriceRepository;

	@Autowired
	ProductService productService;

	@Override
	public int countAllImportProductPrice() {
		try {
			return importProductPriceRepository.countAllWImportProductPrice();
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<ImportProductPrice> findImportProductPricePageable(int limit, int offset) {
		try {
			return importProductPriceRepository.findImportProductPricePageable(limit, offset);
		} catch (Exception e) {
		}
		return null;
	}

}
