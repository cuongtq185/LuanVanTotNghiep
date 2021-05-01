package vn.com.unit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.entity.ImportProductCard;
import vn.com.unit.repository.ImportProductCardRepository;
import vn.com.unit.service.ImportProductCardService;
import vn.com.unit.service.ProductService;

@Service
@Transactional
public class ImportProductCardServiceImpl implements ImportProductCardService {

	@Autowired
	ImportProductCardRepository importProductCardRepository;

	@Autowired
	ProductService productService;

	@Override
	public int countAllImportProductCard() {
		try {
			return importProductCardRepository.countAllWImportProductCard();
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<ImportProductCard> findImportProductCardPageable(int limit, int offset) {
		try {
			return importProductCardRepository.findImportProductCardPageable(limit, offset);
		} catch (Exception e) {
		}
		return null;
	}
	
	public void importProductNew(ImportProductCard imp) {
		try {
			importProductCardRepository.importProductNew(imp.getName(), imp.getImpDetail(), imp.getImpQuantity(), imp.getImpProductPrice());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
