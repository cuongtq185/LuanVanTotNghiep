package vn.com.unit.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.com.unit.dto.CartDto;
import vn.com.unit.entity.CartItem;
import vn.com.unit.entity.CartProduct;
import vn.com.unit.repository.CartItemRepository;
import vn.com.unit.repository.CartRepository;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	AccountService accountService;

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public List<CartDto> findAllCartItemByCurrentAccount() {

		Long curent_account_id = accountService.findCurrentAccount().getAccountId();

		return this.findAllCartItemByAccountId(curent_account_id);
	}

	@Override
	public int countAllCartItemByCurrentAccount(Long account_id) {

		int total = cartRepository.countAllCartItemByAccountId(account_id);

		return total;
	}

	@Override
	public List<CartDto> findAllCartItemByAccountId(Long account_id) {

		return cartRepository.findAllCartItemByAccountId(account_id);
	}

	@Override
	public Long calculateCartTotalByCurrentAccount() {
		Long account_id = accountService.findCurrentAccount().getAccountId();
		return cartRepository.calculateCartTotalByAccountId(account_id);
	}

	@Override
	public void deleteCartItemCurrentAccount(Long product_id, Long account_id) {

		cartRepository.deleteCartItemCurrentAccount(account_id, product_id);
	}

	@Override
	public void addCartItemCurrentAccount(Long product_id, int quantity) {
		Long curent_account_id = accountService.findCurrentAccount().getAccountId();

//		select quantity
//		from p2p_cart
//		where account = 1 and product = 2

		// Nead optimize here
		Integer quantity_in_cart = cartRepository.findProductQuantityInCart(curent_account_id, product_id);

//		if (quantity_in_cart == null) {
//			cartRepository.addCartItemCurrentAccount(curent_account_id, product_id, quantity);
//		} else {
//			cartRepository.addCartItemCurrentAccount(curent_account_id, product_id, quantity_in_cart + quantity);
//		}

		Integer quantity_new = quantity;
		if (quantity_in_cart != null) {
			quantity_new = quantity;
		}

		CartProduct cart_temp = new CartProduct();
		cart_temp.setAccount(curent_account_id);
		cart_temp.setStatus(1);
		cart_temp.setCartCreateAt(new Date());
		
		cartRepository.save(cart_temp);
		
		//Long id = cartRepository.getIdCartCurrent(curent_account_id);		
		CartItem item = new CartItem();
		item.setCart(cart_temp.getCartId());
		item.setProduct(product_id);
		item.setQuantity(quantity_new);
		cartItemRepository.save(item);
		
	}

}
