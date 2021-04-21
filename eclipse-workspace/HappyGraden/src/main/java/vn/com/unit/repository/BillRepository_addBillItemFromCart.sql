declare @bill_id int = /*bill_id*/''
declare @current_account int = /*account_id*/''

-- number of shop in cart of current account
BEGIN
	insert into bill_item (bill, product, quantity)
		(
		select @bill_id as bill, cart_item.product, cart_item.quantity
		from cart
		left join cart_item on cart.cart_id = cart_item.cart 
		where cart.account = @current_account and cart.status = 1
		);

	update cart 
	set status = 0
	where account = @current_account

END;