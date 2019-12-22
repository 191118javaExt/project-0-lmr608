DROP TABLE IF EXISTS employee;

create table employee (
	employee_id SERIAL PRIMARY KEY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	password VARCHAR (50) NOT NULL,
	email VARCHAR (100) UNIQUE,
	admin BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS customer;

create table customer (
	customer_id SERIAL PRIMARY KEY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	username VARCHAR (50) UNIQUE NOT NULL,
	password VARCHAR (50) NOT NULL,
	email VARCHAR (100) UNIQUE
);

DROP TABLE IF EXISTS account;

CREATE TABLE account (
	account_id SERIAL PRIMARY KEY,
	customer_id SERIAL REFERENCES "project0New".customer (customer_id),
	balance NUMERIC (20, 2) DEFAULT 0,
	approved BOOLEAN
);

INSERT INTO employee (first_name, last_name, password, email, admin)
VALUES ('Doug', 'Pederson', 'flyeaglesfly', 'dpederson@eagles.com', true);
INSERT INTO employee (first_name, last_name, password, email, admin)
VALUES ('Carson', 'Wentz', 'flyeaglesfly', 'cwentz@eagles.com', false);
INSERT INTO employee (first_name, last_name, password, email, admin)
VALUES ('Fletcher', 'Cox', 'flyeaglesfly', 'fcox@eagles.com', false);

INSERT INTO customer (first_name, last_name, username, password, email)
VALUES ('Larry', 'Ross', 'user1', 'password1', 'lross@email.com');
INSERT INTO customer (first_name, last_name, username, password, email)
VALUES ('Josh', 'Ross', 'user2', 'password2', 'jross@email.com');
INSERT INTO customer (first_name, last_name, username, password, email)
VALUES ('Steven', 'Ross', 'user3', 'password3', 'sross@email.com');

INSERT INTO account (customer_id, balance, approved)
VALUES (1, 100, FALSE);
INSERT INTO account (customer_id, balance, approved)
VALUES (2, 10000, TRUE);
INSERT INTO account (customer_id, balance, approved)
VALUES (3, 1000000, TRUE);

SELECT * FROM employee;
SELECT * FROM customer;
SELECT * FROM account;