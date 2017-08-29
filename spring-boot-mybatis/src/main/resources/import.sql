drop table if exists product;
drop table if exists user;

create table product (productId varchar primary key, productName varchar not null, price int not null, description varchar);
insert into product (productId, productName, price, description) values ('P100001', 'Galaxy S7', 800000, '갤럭시S7');
insert into product (productId, productName, price, description) values ('P100002', 'iPhone 7', 950000, '아이폰7');
insert into product (productId, productName, price, description) values ('P100003', 'LG G6', 540000, '엘지G6');
insert into product (productId, productName, price, description) values ('P100004', 'Sony XPERIA XZ Premium', 600000, '소니 엑스페리아XZ');

create table user (userId varchar primary key, userName varchar not null , userNumber int not null , birthday varchar);
insert into user (userId, userName, userNumber, birthday) values ('sampleuser1','User1',10000002, '19881111');
insert into user (userId, userName, userNumber, birthday) values ('sampleuser2','User2',10000003, '19920123');
insert into user (userId, userName, userNumber, birthday) values ('sampleuser3','User3',10000004, null);