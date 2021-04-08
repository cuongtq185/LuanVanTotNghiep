select cart.*, d.product_img, e.product_price
from
	(
	select *
	from cart as a
	left join cart_item as b on a.cart_id = b.cart 	
	where a.account = /*account_id*/'2'
	) as cart
left join product as c
on c.product_id = cart.product
left join product_img2D as d on c.product_id = d.id_product_img2D
left join product_price as e on c.product_id = e.product_price_id
where cart.status = 1