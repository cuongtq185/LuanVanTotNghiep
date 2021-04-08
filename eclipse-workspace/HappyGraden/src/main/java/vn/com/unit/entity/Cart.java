package vn.com.unit.entity;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.Table;

@Table(name = "cart_item")
public class Cart {
	
	@Column(name = "cartitem_id")
	private Long cartitemId;
	
	@Column(name = "account")
	private Long account;
	
	@Column(name = "cart")
	private Long cart;
	
	@Column(name = "product")
	private Long product;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "status")
	private int status;

	public Cart() {
	}

	public Long getCartitemId() {
		return cartitemId;
	}

	public void setCartitemId(Long cartitemId) {
		this.cartitemId = cartitemId;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public Long getCart() {
		return cart;
	}

	public void setCart(Long cart) {
		this.cart = cart;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
