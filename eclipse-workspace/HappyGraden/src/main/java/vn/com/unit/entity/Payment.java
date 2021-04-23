package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "payment")
public class Payment {

	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "payment_id")
	private Long paymentId;

	@Column(name = "payment_information")
	private String paymentInformation;

	@Column(name = "payment_status")
	private int paymentStatus;

	@Column(name = "bill")
	private Long bill;

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Long getBill() {
		return bill;
	}

	public void setBill(Long bill) {
		this.bill = bill;
	}

}
