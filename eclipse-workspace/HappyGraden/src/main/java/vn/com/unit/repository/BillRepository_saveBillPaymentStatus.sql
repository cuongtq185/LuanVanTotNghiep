update payment
set payment_status = /*payment_status*/''
where payment_id = (select payment_id from bill where bill_id = /*bill_id*/)