-- disesuaikan dengan user db yang di pakai

--create schema
CREATE SCHEMA payment AUTHORIZATION postgres;


-- payment.db_seq_cust definition 
CREATE SEQUENCE payment.db_seq_cust
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
	START 1
	CACHE 1
	CYCLE;

-- payment.db_seq_wallet definition
CREATE SEQUENCE payment.db_seq_wallet
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
	START 1
	CACHE 1
	CYCLE;
	
-- payment.customer definition 

CREATE TABLE payment.customer (
	cust_id int4 NOT NULL DEFAULT nextval('payment.db_seq_cust'::regclass),
	cust_full_name varchar NOT NULL,
	email varchar NULL,
	gender varchar NULL,
	phone_number varchar NULL,
	cust_type varchar NULL,
	refferal_code varchar NULL,
	cust_pin_access varchar NULL,
	rekening_no varchar NULL,
	status varchar NULL,
	created_date timestamp NULL DEFAULT now(),
	created_by varchar NULL,
	modified_date timestamp NULL,
	modified_by varchar NULL,
	CONSTRAINT customer_pk PRIMARY KEY (cust_id)
);

-- payment."transaction" definition 

CREATE TABLE payment."transaction" (
	transaction_id int4 NOT NULL DEFAULT nextval('payment.db_seq_cust'::regclass),
	cust_id int4 NOT NULL,
	transaction_type varchar NULL,
	transaction_date timestamp NULL,
	transaction_desc varchar NULL,
	id_device varchar NULL,
	beneficiary_cust_id int4 NULL,
	status varchar NULL,
	created_date timestamp NULL DEFAULT now(),
	created_by varchar NULL,
	modified_date timestamp NULL,
	modified_by varchar NULL,
	key_id varchar NULL,
	transfer_order_id varchar NULL,
	key_id_refund varchar NULL,
	CONSTRAINT transaction_pk PRIMARY KEY (transaction_id)
);


-- payment."transaction" foreign keys

ALTER TABLE payment."transaction" ADD CONSTRAINT transaction_fk FOREIGN KEY (cust_id) REFERENCES payment.customer(cust_id);



-- payment.wallet definition 

CREATE TABLE payment.wallet (
	wallet_id int4 NOT NULL DEFAULT nextval('payment.db_seq_wallet'::regclass),
	cust_id int4 NOT NULL,
	wallet_type varchar NOT NULL,
	balance numeric(38, 2) NULL,
	created_date timestamp NULL DEFAULT now(),
	created_by varchar NULL DEFAULT 'System'::character varying,
	modified_date timestamp NULL,
	modified_by varchar NULL,
	status varchar NULL DEFAULT 'Active'::character varying,
	customer_cust_id int4 NULL,
	CONSTRAINT wallet_pk PRIMARY KEY (wallet_id)
);


-- payment.wallet foreign keys

ALTER TABLE payment.wallet ADD CONSTRAINT wallet_fk FOREIGN KEY (cust_id) REFERENCES payment.customer(cust_id);

-- payment.transaction_detail definition 

CREATE TABLE payment.transaction_detail (
	transaction_detail_id int4 NOT NULL DEFAULT nextval('payment.db_seq_cust'::regclass),
	transaction_id int4 NOT NULL,
	wallet_id int4 NOT NULL,
	amount numeric(38, 2) NULL,
	status varchar NULL,
	created_date timestamp NULL,
	created_by varchar NULL,
	modified_date timestamp NULL,
	modified_by varchar NULL,
	payment_method varchar NULL,
	CONSTRAINT transaction_dtl_pk PRIMARY KEY (transaction_detail_id)
);


-- payment.transaction_detail foreign keys

ALTER TABLE payment.transaction_detail ADD CONSTRAINT transaction_detail_fk FOREIGN KEY (transaction_id) REFERENCES payment."transaction"(transaction_id);
ALTER TABLE payment.transaction_detail ADD CONSTRAINT transaction_detail_wallet_fk FOREIGN KEY (wallet_id) REFERENCES payment.wallet(wallet_id);

-- payment.transaction_session definition 

CREATE TABLE payment.transaction_session (
	transaction_session_id int4 NOT NULL DEFAULT nextval('payment.db_seq_cust'::regclass),
	cust_id int4 NOT NULL,
	key_id varchar NOT NULL,
	session_time timestamp NULL DEFAULT now(),
	transaction_type varchar NULL,
	status varchar NULL,
	CONSTRAINT transaction_session_pk PRIMARY KEY (transaction_session_id)
);

INSERT INTO payment.customer
(cust_id, cust_full_name, email, gender, phone_number, cust_type, refferal_code, cust_pin_access, rekening_no, status, created_date, created_by, modified_date, modified_by)
VALUES(nextval('payment.db_seq_cust'::regclass), 'IWAN', 'iwan@gmail.com', 'MALE', '08565689999', 'BASIC', 'IWANKP80', '212606ddb72c74baf7fd06b73ce6d2d7 ', '3125520102', 'Active', '2023-09-23 21:27:26.631', 'System', NULL, NULL);
INSERT INTO payment.customer
(cust_id, cust_full_name, email, gender, phone_number, cust_type, refferal_code, cust_pin_access, rekening_no, status, created_date, created_by, modified_date, modified_by)
VALUES(nextval('payment.db_seq_cust'::regclass), 'SAMSUL', 'samsul@gmail.com', 'MALE', '123113132', 'PLATINUM', 'SAMSA890', '212606ddb72c74baf7fd06b73ce6d2d7 ', '123456789', 'Active', '2023-09-23 21:33:28.903', NULL, NULL, NULL);

INSERT INTO payment.wallet
(wallet_id, cust_id, wallet_type, balance, created_date, created_by, modified_date, modified_by, status, customer_cust_id)
VALUES(nextval('payment.db_seq_wallet'::regclass), (select cust_id  from payment.customer where cust_full_name = 'IWAN'), 'GPAY', 60000.00, '2023-09-23 21:39:34.342', 'System', '2023-09-24 21:42:42.256', '1', 'Active', NULL);
INSERT INTO payment.wallet
(wallet_id, cust_id, wallet_type, balance, created_date, created_by, modified_date, modified_by, status, customer_cust_id)
VALUES(nextval('payment.db_seq_wallet'::regclass), (select cust_id  from payment.customer where cust_full_name = 'SAMSUL'), 'GPAY', 25000.00, '2023-09-24 21:19:18.204', 'System', '2023-09-24 21:42:42.251', '1', 'Active', NULL);

 

