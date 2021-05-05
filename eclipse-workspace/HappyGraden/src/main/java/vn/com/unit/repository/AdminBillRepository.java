package vn.com.unit.repository;

import java.util.List;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.repository.query.Param;

import vn.com.unit.entity.Bill;

public interface AdminBillRepository extends MirageRepository<Bill, Long>{

	public int countAllBill();

	public List<Bill> findAllBill(@Param("sizeOfPage") Integer sizeOfPage,
			@Param("offset") Integer offset);
}
