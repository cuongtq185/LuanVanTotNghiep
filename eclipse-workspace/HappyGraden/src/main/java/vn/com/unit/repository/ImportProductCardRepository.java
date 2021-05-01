package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.ImportProductCard;

public interface ImportProductCardRepository extends MirageRepository<ImportProductCard, Long> {

	public int countAllWImportProductCard();

	public List<ImportProductCard> findImportProductCardPageable(@Param("sizeOfPage") Integer sizeOfPage,
			@Param("offset") Integer offset);
	
	@Modifying
	public void importProductNew(@Param("name") String name, @Param("detail") String detail, @Param("quantity") int quantity, @Param("price") float price); 

}
