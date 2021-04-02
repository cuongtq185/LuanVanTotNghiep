package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.WareHouse;

public interface WareHouseRepository extends MirageRepository<WareHouse, Long>{
	
	public int countAllWareHouse();
	
	public List<WareHouse> findWareHousePageable(@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset);
	
	public void updateProductById(@Param("id") Long id, @Param("detail") String detail, 
			@Param("quantity") int quantity, @Param("price") float price );
	
	public void updateQuantity(@Param("id") Long id, @Param("quantity") int quantity);
	
	@Modifying
	public void insert(@Param("id") int id);
	
	@Modifying
	public WareHouse insertWareHouse(@Param("id") int id, @Param("quantity") int quantity);

}
