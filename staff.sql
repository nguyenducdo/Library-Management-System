INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Tran Bao Hieu','Thanh Hoa','012464442','boy123@gmail.com','helloboy','2',1);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('nguyen duc anh','Quoc Oai- Ha Noi','012464414','boynhangheo2@gmail.com','osaka','1',1);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Luong Duc Nguyen','Quoc Oai- Ha Noi','012464333','hellogirl@gmail.com','rickkid123','111',1);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Nguyen Tri HIeu','Quoc Oai- Ha Noi','012464444','boynhangheo@gmail.com','obama','1',1);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Ngo Ba Kha','Bac Ninh','09888888','khabanh@gmail.com','khabanh','ditu',1);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Nguyen Van Nhi',' Ha Noi','016882288','abcreturn_bookdetail_billxyzdewq@gmail.com','kyotoaizu','222',0);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Nguyen Anh Khoa','Quoc Oai- Ha Noi','012460000','lustu1100@gmail.com','baycaonao','123',1);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Nguyen Duc Dung','Thuan Thanh-Bac Ninh','013333344','hellokiity@gmail.com','hahaha','00000',1);
select * from staff
select * from member


SELECT * FROM staff WHERE username = 'khabanh' AND password = 'ditu'

alter table return_book add foreign key (id_staff) references staff(id);
alter table return_book add foreign key (id_bill) references detail_bill(id_bill);
alter table return_book add foreign key (id_book) references detail_bill(id_book);

alter table detail_bill add foreign key (id_book) references book(id_book)
alter table detail_bill add foreign key (id_bill) references borrow_book(id_bill)

alter table borrow_book add foreign key (id_member) references member(id_member)
alter table borrow_book add foreign key (id_staff) references staff(id)

insert into borrow_book values (null,'M0002','5','2019-5-12');
insert into detail_bill values ('BB000002','B00772','2019-5-20',default);
insert into return_book values ('BB000001','B00772','2','2019-5-18');

select * from detail_bill
select * from borrow_book
select * from return_book