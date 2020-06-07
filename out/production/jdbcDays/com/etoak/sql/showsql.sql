drop table if exists person;

create table person(
  id     INT PRIMARY KEY AUTO_INCREMENT,
  name   VARCHAR(10) NOT NULL,
  pass   VARCHAR(10) NOT NULL,
  gender VARCHAR(4),
  age    INT             DEFAULT 20,
  hobby  VARCHAR(50),
  salary DOUBLE,
  email  VARCHAR(30),
  birth  DATE,
  level  VARCHAR(4),
  status VARCHAR(4)
);

insert into person values (null,'admin','admin','1',40,null,5000.00,'admin@etoak.com','1990-04-04','1','1');
insert into person values (null,'jack','123456','1',20,null,4000.00,'admin1@etoak.com','1990-05-21','0','1');
insert into person values (null,'tom','111111','0',22,null,6000.00,'admin2@etoak.com','1997-01-23','0','1');
insert into person values (null,'jarry','222222','0',33,null,4000.00,'admin3@etoak.com','1993-02-04','0','1');
insert into person values (null,'black','333333','1',14,null,2000.00,'admin4@etoak.com','2005-07-07','0','1');
