package vn.com.unit.repository;

import org.springframework.data.mirage.repository.MirageRepository;

import vn.com.unit.entity.CartItem;

public interface CartItemRepository extends MirageRepository<CartItem, Long>{

}
