﻿
drop table log;
drop table payment;
drop table bill_item;
drop table bill;
drop table promotion;
drop table cart_item;
drop table cart;
drop table product_price;
drop table product_img3D;
drop table product_img2D;
drop table rate;
drop table import_product_card;
drop table warehouse;
drop table product;
drop table origin;
drop table category;
drop table account;
drop table role;
	


create table role(
	role_id bigint primary key identity(1,1),
	role_name nvarchar(50)
)

create table account(
	account_id bigint primary key identity(1,1),
	account_name nvarchar(50),
	account_username nvarchar(50) not null unique,
	account_password nvarchar(250)not null,
	account_email nvarchar(50),
	account_phone nvarchar(20),
	account_address nvarchar(250),
	account_disable bit default 0,
	account_createAt datetime default getutcdate(),
	role bigint,
	constraint fk_account_id_role_id foreign key (role) references role(role_id) 
)


create table category(
	category_id bigint primary key identity(1,1),
	category_name nvarchar(50) not null,
	category_disable  bit default 0
)


create table origin(
	origin_id bigint primary key identity(1,1),
	origin_name nvarchar(50) not null,
	origin_disable  bit default 0
)

create table product(
	product_id bigint primary key identity(1,1),
	product_name nvarchar(255),
	product_detail nvarchar(255),
	product_disable bit default 0,
	--product_createAt datetime,
	category bigint not null,
	origin bigint not null,

	constraint fk_category_id_product_id foreign key (category) references category(category_id),
	constraint fk_origin_id_product_id foreign key (origin) references origin(origin_id)

)

create table warehouse(
	product bigint,
	product_quantity int,

	constraint fk_product_id_product foreign key (product) references product(product_id)
)

create table import_product_card(
	imp_id bigint primary key identity(1,1),
	--product bigint,
	[name] nvarchar(255),
	imp_detail nvarchar(250),
	imp_createAt datetime default getutcdate(),
	imp_quantity int,
	imp_product_price float
	
	--constraint fk_product_imp_product foreign key (product) references product(product_id)
)

create table rate(
	rate_id bigint primary key identity(1,1),
	rate_point float,
	rate_createAt datetime default getutcdate(),
	rate_status tinyint,
	account bigint,

	constraint fk_account_id_rate_id foreign key (account) references account(account_id)
)

create table product_img2D(
	id_product_img2D bigint,
	product_img nvarchar(250),

	constraint fk_product_id_id_product_img2D foreign key(id_product_img2D) references product(product_id)
)

create table product_img3D(
	id_product_img3D bigint,
	product_img nvarchar(250),

	constraint fk_product_id_id_product_img3D foreign key(id_product_img3D) references product(product_id)
)

create table product_price(
	create_at datetime default getutcdate() primary key,
	product_price_id bigint,
	product_price float,

	constraint fk_product_id_product_price_id foreign key(product_price_id) references product(product_id)
)

create table cart(
	cart_id bigint primary key identity(1,1),
	cart_createAt datetime default getutcdate(),
	account bigint,
	status int,
	constraint fk_account_id_cart foreign key(account) references account(account_id)
)

create table cart_item(
	cartitem_id bigint primary key identity(1,1),
	cart bigint,
	product bigint,
	quantity int,

	constraint fk_product_id_products foreign key(product) references product(product_id),
	constraint fk_product_id_cartitem_id foreign key(cart) references cart(cart_id)
	
)

create table promotion(
	promotion_id bigint primary key identity(1,1),
	promotion_discount bigint,
	start_date datetime,
	end_date datetime,
	product_promotion bigint,

	constraint fk_product_id_product_promotion foreign key(product_promotion) references product(product_id)
)

create table bill(
	bill_id bigint primary key identity(1,1),
	address nvarchar(250),
	phone nvarchar(20),
	full_name nvarchar(250),
	bill_createAt datetime default getutcdate(),
	--bill_status tinyint,
	tottal_price float,
	total_quantity int,
	--bill_cart bigint,
	account bigint,
	--payment_id bigint,

	--constraint fk_cart_id_bill_id foreign key (bill_cart) references cart(cart_id),
	constraint fk_cart_id_account_aa foreign key (account) references account(account_id)
	--constraint fk_bill_payment foreign key (payment_id) references payment(payment_id)
)

create table bill_item(
	id bigint primary key identity(1,1),
	bill bigint,
	product bigint,
	quantity int,

	constraint fk_bill_item foreign key (bill) references bill(bill_id),
	constraint fk_bill_item_product foreign key (product) references product(product_id),

)

create table payment(
	payment_id bigint primary key identity(1,1),
	payment_information nvarchar(250),
	payment_status int,
	bill bigint,

	constraint fk_bill_payment foreign key (bill) references bill(bill_id)
)

create table log (
	id bigint primary key identity(1,1),
	log nvarchar(3000),
	type nvarchar(50),
	author nvarchar(500),
	create_at datetime default getutcdate(),
)

--insert
insert into role(role_name) values ('ROLE_ADMIN')
insert into role(role_name) values ('ROLE_USER')

--insert account
insert into account(account_name, account_username, account_password, account_email,account_phone, account_address, role)
values (N'Trần Quốc Cường', 'cuongtq', '', 'cuongtq.tvh@gmail.com','0398915767', 'Châu Thành - Trà Vinh', 1)

insert into account(account_name, account_username, account_password, account_email,account_phone, account_address, role)
values (N'Trần Quốc Vinh', 'user', '', 'vinh@gmail.com','0123456789', 'Châu Thành - Trà Vinh', 2)

--insert into account(account_name, account_username, account_password, account_email,account_phone, account_address, role)
--values (N'test1', 'test1', '', 'test1@gmail.com','0123456789', 'Châu Thành - Trà Vinh', 2)

--insert into account(account_name, account_username, account_password, account_email,account_phone, account_address, role)
--values (N'test2', 'test2', '', 'test2@gmail.com','0123456789', 'Châu Thành - Trà Vinh', 2)

--insert into account(account_name, account_username, account_password, account_email,account_phone, account_address, role)
--values (N'test3', 'test3', '', 'test3@gmail.com','0123456789', 'Châu Thành - Trà Vinh', 2)

--insert into account(account_name, account_username, account_password, account_email,account_phone, account_address, role)
--values (N'test4', 'test4', '', 'test4@gmail.com','0123456789', 'Châu Thành - Trà Vinh', 2)

--insert into account(account_name, account_username, account_password, account_email,account_phone, account_address, role)
--values (N'test5', 'test5', '', 'test5@gmail.com','0123456789', 'Châu Thành - Trà Vinh', 2)

--insert category
insert into category( category_name) values (N'Cây phong thủy')
insert into category( category_name) values (N'Cây cảnh trong nhà')
insert into category( category_name) values (N'Cây cảnh văn phòng')

--insert origi
	insert into origin( origin_name) values (N'Đà Lạt')
	insert into origin( origin_name) values (N'Trà Vinh')
	insert into origin( origin_name) values (N'Đồng Nai')
	insert into origin( origin_name) values (N'Vĩnh Long')

----insert product
--insert into product(product_name,product_detail,product_disable, category, origin)
--values(N'Cây Ngọc Ngân', N' Hợp với tuổi dần', 0,4,2)
--insert into product(product_name,product_detail,product_disable, category, origin)
--values(N'Cây bạch mã hoàng tử', N' Hợp với tuổi dần', 0,4,2)

----insert product_price
--insert into product_price(create_at, product_price_id, product_price)
--values(getutcdate(),1, 2000)
--insert into product_price(create_at, product_price_id, product_price)
--values(getutcdate(),2,3000)

----insert product-img2D
--insert into product_img2D(id_product_img2D,	product_img)
--values(1,'/static/img/1.jpg')
--insert into product_img2D(id_product_img2D,	product_img)
--values(2,'/static/img/2.jpg')
