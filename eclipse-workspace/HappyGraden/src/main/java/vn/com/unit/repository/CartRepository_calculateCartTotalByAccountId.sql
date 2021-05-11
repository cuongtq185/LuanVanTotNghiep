select sum(item.quantity*price.product_price)
from cart as cart
left join cart_item as item on cart.cart_id = item.cartitem_id
left join product_price as price on price.product_price_id = item.product
where cart.account = /*accountId*/'' and cart.status = 1