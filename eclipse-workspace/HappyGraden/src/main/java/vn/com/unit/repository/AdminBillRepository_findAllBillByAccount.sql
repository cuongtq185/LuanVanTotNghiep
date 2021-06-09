select a.*, b.payment_status as status, c.*, d.product_name
from bill as a
left join payment as b on a.bill_id = b.bill
left join bill_item as c on b.bill = c.bill
left join product as d on d.product_id = c.product
where a.account = /*id*/''
ORDER BY a.bill_id desc
