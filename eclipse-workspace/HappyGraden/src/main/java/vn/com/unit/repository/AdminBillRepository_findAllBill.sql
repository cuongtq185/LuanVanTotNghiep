select a.*, b.payment_status as status
from bill as a
left join payment as b on a.bill_id = b.bill
ORDER BY a.bill_id desc
OFFSET  /*offset*/ ROWS FETCH NEXT  /*sizeOfPage*/ ROWS ONLY