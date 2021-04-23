select *, payment.payment_status as payment
from bill
left join payment on bill.bill_id = payment.bill
where account = /*account_id*/''
	and bill_id =  /*bill_id*/''