select sum(item.quantity)
from cart as cart
left join cart_item as item on cart.cart_id = item.cartitem_id
where cart.account = /*accountId*/'' and cart.status = 1