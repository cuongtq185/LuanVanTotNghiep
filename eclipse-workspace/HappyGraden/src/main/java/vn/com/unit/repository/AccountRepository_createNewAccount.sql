insert into ACCOUNT(account_name, account_username, account_password,account_email, account_phone, account_address, account_disable, account_createAt, role) 
	values (/*name*/,/*username*/, /*password*/, /*email*/, /*phone*/, /*address*/, 0, GETDATE(), 2);
	
SELECT SCOPE_IDENTITY();