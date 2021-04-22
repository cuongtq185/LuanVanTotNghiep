declare @i int = (select cart_id from cart
left join cart_item on cart_item.cart = cart.cart_id
where cart.account = /*account_id*/'' and cart_item.product = /*product_id*/'')

delete from cart_item
where cart = @i
delete from cart
where cart_id = @i