select count (*)
from
	(
	select *
	from cart as a
	left join cart_item as b on a.cart_id = b.cart 	
	) as cart
left join product as c
on c.product_id = cart.product
where cart.status = 1