package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.ImportProductPrice;

public interface ImportProductPriceRepository extends MirageRepository<ImportProductPrice, Long>{
	
	public int countAllWImportProductPrice();

	public List<ImportProductPrice> findImportProductPricePageable(@Param("sizeOfPage") Integer sizeOfPage,
			@Param("offset") Integer offset);

}