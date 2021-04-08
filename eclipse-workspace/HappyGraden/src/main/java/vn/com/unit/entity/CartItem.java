package vn.com.unit.entity;

import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "cart_item")
public class CartItem {
	
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY)
	@Column(name = "cartitem_id")
	private Long cartitemId;
	
	@Column(name = "cart")
	private Long cart;
	
	@Column(name = "product")
	private Long product;
	
	@Column(name = "quantity")
	private int quantity;

	public CartItem() {
	}

	public Long getCartitemId() {
		return cartitemId;
	}

	public void setCartitemId(Long cartitemId) {
		this.cartitemId = cartitemId;
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

}
