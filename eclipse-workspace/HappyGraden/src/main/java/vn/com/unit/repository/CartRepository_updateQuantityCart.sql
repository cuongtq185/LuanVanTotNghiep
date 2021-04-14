update cart_item
	set quantity = /*quantity*/'2'
	where cart = 
	(select cart_id
	from cart as a
	left join cart_item as b on a.cart_id = b.cart 	
	where a.account = /*account_id*/'2' and b.product = /*product_id*/'1')