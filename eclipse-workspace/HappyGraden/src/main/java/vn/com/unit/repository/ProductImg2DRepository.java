package vn.com.unit.repository;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.ProductImg2D;

public interface ProductImg2DRepository extends MirageRepository<ProductImg2D, Long>{

	public void saveImg2D(@Param("id") int id, @Param("url") String url);
}
