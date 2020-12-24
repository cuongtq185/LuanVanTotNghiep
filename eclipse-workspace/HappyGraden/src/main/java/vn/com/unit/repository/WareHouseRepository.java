package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.WareHouse;

public interface WareHouseRepository extends MirageRepository<WareHouse, Long>{
	
	public int countAllWareHouse();
	
	public List<WareHouse> findWareHousePageable(@Param("sizeOfPage") Integer sizeOfPage,@Param("offset") Integer offset);
	
	public void updateProductById(@Param("id") Long id, @Param("detail") String detail, 
			@Param("quantity") int quantity, @Param("price") int price );
	
	public void updateQuantity(@Param("id") Long id, @Param("quantity") int quantity);
	
	public void insert(@Param("id") Long id);

}
