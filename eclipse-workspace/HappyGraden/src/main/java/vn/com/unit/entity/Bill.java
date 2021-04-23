package vn.com.unit.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "bill")
public class Bill {

	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY) // Primary key // Auto increment
	@Column(name = "bill_id")
	private Long billId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "bill_createAt")
	private Date billCreateAt;
	
//	@Column(name = "bill_status")
//	private int billStatus;
	
	@Column(name = "total_quantity")
	private int totalQuantity;
	
	@Column(name = "tottal_price")
	private float tottalPrice;
	
	@Column(name = "bill_cart")
	private int bill_cart;
	
	@Column(name ="account")
	private Long account;
	
//	@Column(name = "payment_id")
	private int payment;
	
	
	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Bill() {
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBillCreateAt() {
		return billCreateAt;
	}

	public void setBillCreateAt(Date billCreateAt) {
		this.billCreateAt = billCreateAt;
	}

//	public int getBillStatus() {
//		return billStatus;
//	}
//
//	public void setBillStatus(int billStatus) {
//		this.billStatus = billStatus;
//	}

	
	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public float getTottalPrice() {
		return tottalPrice;
	}

	public void setTottalPrice(float tottalPrice) {
		this.tottalPrice = tottalPrice;
	}

	public int getBill_cart() {
		return bill_cart;
	}

	public void setBill_cart(int bill_cart) {
		this.bill_cart = bill_cart;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

//	public Long getPaymentId() {
//		return paymentId;
//	}
//
//	public void setPaymentId(Long paymentId) {
//		this.paymentId = paymentId;
//	}

}