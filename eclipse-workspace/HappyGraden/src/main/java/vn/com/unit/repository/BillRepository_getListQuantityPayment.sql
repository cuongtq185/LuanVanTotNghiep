select bill_item.*
from bill_item
left join bill on bill.bill_id = bill_item.bill
where bill_item.bill = /*bill_id*/