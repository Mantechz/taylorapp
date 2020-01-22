
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values ('S001', 'Colour jacket', 10000, sysdate);
 
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values ('S002', 'General', 5000, sysdate);
 
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values ('S003', 'Agbada', 12000, sysdate);
 
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values ('S004', 'Agbada', 12000, sysdate);
 
insert into products (CODE, NAME, PRICE, CREATE_DATE)
values ('S005', 'Sharp Suit', 15000, sysdate);

insert into fabrics (CODE, NAME, PRICE, CREATE_DATE)
values ('0', ' - ',0, sysdate );
insert into fabrics (CODE, NAME, PRICE, CREATE_DATE)
values ('F001', 'blue wine', 10000, sysdate);
 
insert into fabrics (CODE, NAME, PRICE, CREATE_DATE)
values ('F002', 'green black', 5000, sysdate);
 
insert into fabrics (CODE, NAME, PRICE, CREATE_DATE)
values ('F003', 'high red', 12000, sysdate);
 
insert into fabrics (CODE, NAME, PRICE, CREATE_DATE)
values ('F004', 'blood glue', 12000, sysdate);
 
insert into fabrics (CODE, NAME, PRICE, CREATE_DATE)
values ('F005', 'white pink', 15000, sysdate);

insert into roles (ID, NAME) values ('1', 'USER');
insert into users (ID, PASSWORD, USERNAME) values ('1', '$2a$10$aijOC/yWYsCYHqeBvH52F.cYT5X4zGbNcdFj0b6ZeBGUw8ibxN2Du', 'admin'); 
insert into USERS_ROLES (USER_ID, ROLES_ID) values ('1', '1');




