INSERT INTO tbl_property (id_property, price, salesman, status) values (1, 1000, 'Júlia', 'AVAILABLE');
INSERT INTO tbl_property (id_property, price, salesman, status) values (2, 1250, 'Thiago', 'AVAILABLE');
INSERT INTO tbl_property (id_property, price, salesman, status) values (3, 1375, 'Aline', 'AVAILABLE');

INSERT INTO tbl_guarantor (id_guarantor,address, cep, city, cpf, district, name, nationality, phone, profession, rg, uf) values (1,'Rua x','97677228','Alegrete Azul','04408178030','Lagoa Azul','Miro','Brasileiro','99989678','Administrador',1126629815,'RS');
INSERT INTO tbl_guarantor (id_guarantor,address, cep, city, cpf, district, name, nationality, phone, profession, rg, uf) values (2,'Rua Y','97677229','Alegrete Verde','04408178031','Lagoa Preta','Mira','Brasileiro','99989679','Engenheiro',1126629816,'SP');
INSERT INTO tbl_guarantor (id_guarantor,address, cep, city, cpf, district, name, nationality, phone, profession, rg, uf) values (3,'Rua Z','97677230','Alegrete Marrom','04408178032','Lagoa Vermelho','Miru','Brasileiro','99989680','Garçom',1126629817,'PA');

INSERT INTO tbl_customer (id_customer,address, city, cpe, cpf, district, name, salary, uf, id_guarantor) values (1, 'Ivojohann', 'Novo Hamburgo', '97544228','044085556636','Canudos','Adão',1500, 'RS',1);
INSERT INTO tbl_customer (id_customer,address, city, cpe, cpf, district, name, salary, uf, id_guarantor) values (2, 'Vera Cruz', 'Uruguaina', '97544229','044085557736','Villa','Eva',1550, 'SP',2);
INSERT INTO tbl_customer (id_customer,address, city, cpe, cpf, district, name, salary, uf, id_guarantor) values (3, 'Indio Azul', 'Manoel Viana', '97555228','055095557736','Centro','Eva',1667, 'RO',3);
