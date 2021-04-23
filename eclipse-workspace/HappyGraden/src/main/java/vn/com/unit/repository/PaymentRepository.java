package vn.com.unit.repository;

import org.springframework.data.mirage.repository.MirageRepository;
import org.springframework.data.mirage.repository.query.Modifying;

import vn.com.unit.entity.Payment;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends MirageRepository<Payment, Long>{

	@Modifying
	public void createPaymentByBill(@Param("bill_id") Long bill_id);
}
