DROP TABLE IF EXISTS employee;

create table employee (
	employee_id SERIAL PRIMARY KEY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	phone VARCHAR (20),
	email VARCHAR (100) UNIQUE,
	position VARCHAR (10) NOT NULL
);

DROP TABLE IF EXISTS customer;

create table customer (
	customer_id SERIAL PRIMARY KEY,
	first_name VARCHAR (50) NOT NULL,
	last_name VARCHAR (50) NOT NULL,
	username VARCHAR (50) UNIQUE NOT NULL,
	password VARCHAR (50) NOT NULL,
	phone VARCHAR (20),
	email VARCHAR (100) UNIQUE
);

DROP TABLE IF EXISTS account;

CREATE TABLE account (
	account_id SERIAL PRIMARY KEY,
	customer_id SERIAL REFERENCES project0.customer (customer_id),
	balance NUMERIC (20, 2) DEFAULT 0,
	type VARCHAR (10) NOT NULL,
	approved BOOLEAN
);

SELECT * FROM employee;
SELECT * FROM customer;
SELECT * FROM account;

INSERT INTO employee (first_name, last_name, phone, email, position)
VALUES ('Doug', 'Pederson', '215-123-4567', 'dpederson@eagles.com', 'Admin');
INSERT INTO employee (first_name, last_name, phone, email, position)
VALUES ('Carson', 'Wentz', '215-234-5678', 'cwentz@eagles.com', 'Employee');
INSERT INTO employee (first_name, last_name, phone, email, position)
VALUES ('Fletcher', 'Cox', '215-345-6789', 'fcox@eagles.com', 'Employee');

INSERT INTO customer (first_name, last_name, username, password, phone, email)
VALUES ('Larry', 'Ross', 'Eaglesfan1', 'Flyeaglesfly1', '610-123-4567', 'lross@revature.com');
INSERT INTO customer (first_name, last_name, username, password, phone, email)
VALUES ('Josh', 'Ross', 'Eaglesfan2', 'Flyeaglesfly2', '610-234-5678', 'jross@revature.com');
INSERT INTO customer (first_name, last_name, username, password, phone, email)
VALUES ('Steven', 'Ross', 'Eaglesfan3', 'Flyeaglesfly3', '610-345-6789', 'sross@revature.com');

INSERT INTO account (customer_id, balance, type, approved)
VALUES (1, 1000, 'checking', FALSE);
INSERT INTO account (customer_id, balance, type, approved)
VALUES (2, 100000, 'checking', TRUE);
INSERT INTO account (customer_id, balance, type, approved)
VALUES (3, 10000000, 'savings', TRUE);