insert into Role values('ROLE_ADMIN');
insert into Role values('ROLE_COMPRADOR');
insert into User(login,name,password) values('comprador@gmail.com','Comprador','$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');
insert into User(login,name,password) values('admin@casadocodigo.com.br','Administrador','$2a$10$3Qrx0rv8qSmZ8s3RlD5qE.upleP7.Qzbg5EoIAm62evEkY4c023TK');
insert into User_Role(User_login,roles_name) values('comprador@gmail.com','ROLE_COMPRADOR');
insert into User_Role(User_login,roles_name) values('admin@casadocodigo.com.br','ROLE_ADMIN');