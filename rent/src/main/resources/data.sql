INSERT INTO tbl_property (id_property, price, salesman, status) values (1, 1000, 'Júlia', 'AVAILABLE');
INSERT INTO tbl_property (id_property, price, salesman, status) values (2, 1250, 'Thiago', 'AVAILABLE');
INSERT INTO tbl_property (id_property, price, salesman, status) values (3, 1375, 'Aline', 'AVAILABLE');

insert into tbl_address (id_address, address, city, cpe, district, uf) values (1,'Bairro x', 'Alegrete', '97556565','Canudos','RS');
insert into tbl_address (id_address, address, city, cpe, district, uf) values (2,'Bairro y', 'Alegreti', '97556565','Canudos','RS');

insert into tbl_contact_info (id_contact, email, phone) VALUES (1, 'mateusbalda89@gmail.com', '55999896728');
insert into tbl_contact_info (id_contact, email, phone) VALUES (2, 'mateusbalda85@gmail.com', '55999896730');

insert into tbl_personal_info (id_personal, cpf, name) VALUES (1, '04408178055','Joao');
insert into tbl_personal_info (id_personal, cpf, name) VALUES (2, '04408178040','Pedro');

insert into tbl_guarantor (id_guarantor, nationality, profession, rg, id_address, id_contact, id_personal) values (1, 'Brasileiro','Pintor',112626565,1,1,1);
insert into tbl_guarantor (id_guarantor, nationality, profession, rg, id_address, id_contact, id_personal) values (2, 'Brasileira','Atriz',112626570,2,2,2);

insert into tbl_customer (id_customer, salary, id_address, id_guarantor, id_personal) values (1, 1500, 1, 1,1);
insert into tbl_customer (id_customer, salary, id_address, id_guarantor, id_personal) values (2, 1500, 2, 2,1);

insert into tbl_rent (id_rent, condominium, date_adjustment_igpm, description, end_date_rent, energy, iptu, start_date_rent, value, water, id_customer, id_property) values (1, 1500, '2023-01-16', 'adnasdasdas', '2024-01-20', 150, 50, '2023-01-16', 800, 80, 1, 1);

