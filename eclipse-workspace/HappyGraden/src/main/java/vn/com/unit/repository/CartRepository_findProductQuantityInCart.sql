select max(b.quantity)
from cart as a
left join cart_item as b on b.cart = a.cart_id
where a.account = /*account_id*/'' and b.product = /*product_id*/'' and status = 1