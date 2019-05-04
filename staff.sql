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
values('Nguyen Van Nhi',' Ha Noi','016882288','abcxyzdewq@gmail.com','kyotoaizu','222',0);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Nguyen Anh Khoa','Quoc Oai- Ha Noi','012460000','lustu1100@gmail.com','baycaonao','123',1);
INSERT INTO staff(name,address,tel,email,username,password,gender) 
values('Nguyen Duc Dung','Thuan Thanh-Bac Ninh','013333344','hellokiity@gmail.com','hahaha','00000',1);
select * from staff

SELECT * FROM staff WHERE username = 'khabanh' AND password = 'ditu'