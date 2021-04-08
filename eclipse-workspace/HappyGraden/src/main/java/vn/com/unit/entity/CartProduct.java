package vn.com.unit.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;

import jp.sf.amateras.mirage.annotation.Column;
import jp.sf.amateras.mirage.annotation.PrimaryKey;
import jp.sf.amateras.mirage.annotation.Table;
import jp.sf.amateras.mirage.annotation.PrimaryKey.GenerationType;

@Table(name = "cart")
public class CartProduct {
	
	@Id
	@PrimaryKey(generationType = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Long cartId;
	
	@Column(name = "cart_createAt")
	private Date cartCreateAt;
	
	@Column(name = "account")
	private Long account;
	
	@Column(name = "status")
	private int status;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Date getCartCreateAt() {
		return cartCreateAt;
	}

	public void setCartCreateAt(Date cartCreateAt) {
		this.cartCreateAt = cartCreateAt;
	}

	public Long getAccount() {
		return account;
	}

	public void setAccount(Long account) {
		this.account = account;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
