-- DROP TABLE orders_catalog;
-- DROP TABLE catalog;
-- DROP TABLE orders;
-- DROP TABLE categories;
-- DROP TABLE statuses;
-- DROP TABLE users;
-- DROP TABLE roles;
-- DROP TABLE type_hotel;

CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(10) NOT NULL UNIQUE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'manager');
INSERT INTO roles VALUES(2, 'user');

CREATE TABLE users(

-- 'generated always AS identity' means id is autoincrement field
-- (from 1 up to Integer.MAX_VALUE with the step 1)
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,

-- 'UNIQUE' means logins values should not be repeated in login column of table
	login VARCHAR(10) NOT NULL UNIQUE,

-- not null string columns
	password VARCHAR(10) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,

-- this declaration contains the foreign key constraint
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id)

-- removing a row with some ID from roles table implies removing
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in
-- users table with ROLES_ID=ID
		ON DELETE CASCADE

-- the same as previous but updating is used insted deleting
		ON UPDATE RESTRICT,
		ban BOOL NOT NULL DEFAULT FALSE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- id = 1
INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Ivan', 'Ivanov', 0, DEFAULT);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'manager', 'manager', 'Petr', 'Petrov', 1, DEFAULT);
-- id = 3
INSERT INTO users VALUES(DEFAULT, 'user', 'user', 'Василий', 'Забубенский', 2, DEFAULT);

INSERT INTO users VALUES(DEFAULT, 'петров', 'петров', 'Иван', 'Петров', 2, DEFAULT);

----------------------------------------------------------------
-- STATUSES
-- statuses for orders
----------------------------------------------------------------
CREATE TABLE statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- INSERT INTO statuses VALUES(0, 'opened');
INSERT INTO statuses VALUES(0, 'registered');
INSERT INTO statuses VALUES(1, 'paid');
INSERT INTO statuses VALUES(2, 'canceled');

CREATE TABLE categories(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- REST, EXCURSION, SHOPPING;
INSERT INTO categories VALUES(0, 'REST'); -- отдых
INSERT INTO categories VALUES(1, 'EXCURSION'); -- экскурсия
INSERT INTO categories VALUES(2, 'SHOPPING'); -- шоппинг

CREATE TABLE orders(
	id INTEGER NOT NULL PRIMARY KEY,
	bill INTEGER NOT NULL DEFAULT 0,
	user_id INTEGER NOT NULL REFERENCES users(id)

)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE type_hotel(
  id INTEGER NOT NULL PRIMARY KEY,
  name VARCHAR(20) NOT NULL UNIQUE
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO type_hotel VALUES(0,'HOTEL');
INSERT INTO type_hotel VALUES(1,'MOTEL');
INSERT INTO type_hotel VALUES(2,'BOUTIQUE');
INSERT INTO type_hotel VALUES(3,'APARTMENTS');



CREATE TABLE catalog(
  hot BOOLEAN NOT NULL DEFAULT false,
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	price INTEGER NOT NULL,
	category_id INTEGER NOT NULL REFERENCES categories(id),
	type_hotel_id INTEGER NOT NULL REFERENCES type_hotel(id),
	q_people INTEGER NOT  NULL
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- rest                      hot       id     name   price,cat,type,q
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Turkey', 2100, 0,  1,  2); -- 1 (order id)
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Egypt', 2150,  0,  2,  3);      -- 2
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Greece', 2500, 0,  0,  4);
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Finland', 3125,0,  1,  2); -- 1 (order id)
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Portugal',3999,0,  2,  2);      -- 2
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Thailand', 2500,0,  0,  4);
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Serbia', 800, 0,  1,  3); -- 1 (order id)
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'UAE', 4500,  0,  0,  2);      -- 2
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Cyprus', 1700, 0,  3,  4);
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Israel', 600, 0,  3,  2); -- 1 (order id)
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Jordan', 3000,  0,  3,  2);      -- 2
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Andorra', 2650, 0,  1,  3);     -- 3
-- excursion
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Miami', 700,   1,  0,  1);       -- 4
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'London', 500,  1,  2,  2);      -- 5
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Australia',1000,1, 3,  4);
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Spain', 2450,   1,  0,  1);       -- 4
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Austria', 640,  1,  2,  3);      -- 5
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Czech Republic',1900,1, 3,  4);
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Montenegro', 2700, 1,  2,  1);       -- 4
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Gloria', 500,  1,  2,  2);      -- 5
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'China',1200,1, 3,  3);  -- 6
-- shopping
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Italy', 700,   2,  0,  4);       -- 4
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'France', 500,  2,  3,  2);
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'India', 950,   2,  0,  3);       -- 4
INSERT INTO catalog VALUES(DEFAULT,DEFAULT, 'Hungary', 500,  2,  3,  1);  -- 5

CREATE TABLE discount (
id   INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
max INTEGER NOT NULL DEFAULT 25,
step INTEGER NOT NULL DEFAULT 1
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



INSERT INTO discount (id,max,step)
VALUES (DEFAULT,DEFAULT,DEFAULT);


CREATE TABLE orders_catalog(
	order_id INTEGER NOT NULL REFERENCES orders(id),
	catalog_id INTEGER NOT NULL REFERENCES catalog(id),
	status_id INTEGER NOT NULL REFERENCES statuses(id),
	discount INTEGER DEFAULT 0
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

-- INSERT INTO orders_catalog VALUES(1, 7, 0,DEFAULT );
-- INSERT INTO orders_catalog VALUES(1, 5, 0,DEFAULT );
-- INSERT INTO orders_catalog VALUES(1, 8, 0,DEFAULT );

-- INSERT INTO orders_catalog VALUES(2, 3, 0,DEFAULT );
-- INSERT INTO orders_catalog VALUES(2, 4, 0,DEFAULT );

-- INSERT INTO orders_catalog VALUES(3, 5, 0,DEFAULT );
-- INSERT INTO orders_catalog VALUES(3, 6, 0,DEFAULT );

-- INSERT INTO orders_catalog VALUES(4, 6, 0,DEFAULT );
-- INSERT INTO orders_catalog VALUES(4, 7, 0,DEFAULT );
-- INSERT INTO orders_catalog VALUES(4, 8, 0,DEFAULT );

-- Insert the sum of all orders by this id
-- INSERT INTO orders VALUES(1,
-- (SELECT sum(price) FROM mydbtest.catalog INNER JOIN
-- 	(
--     SELECT catalog_id
--     FROM orders_catalog
--     WHERE order_id=1
-- 	) orders_catalog
--     ON catalog.id=orders_catalog.catalog_id
-- ), 1);
-- INSERT INTO orders VALUES(2,
-- (SELECT sum(price) FROM mydbtest.catalog INNER JOIN
-- 	(
--     SELECT catalog_id
--     FROM orders_catalog
--     WHERE order_id=2
-- 	) orders_catalog
--     ON catalog.id=orders_catalog.catalog_id
-- ), 2);
-- INSERT INTO orders VALUES(3,
-- (SELECT sum(price) FROM mydbtest.catalog INNER JOIN
-- 	(
--     SELECT catalog_id
--     FROM orders_catalog
--     WHERE order_id=3
-- 	) orders_catalog
--     ON catalog.id=orders_catalog.catalog_id
-- ), 3);
-- INSERT INTO orders VALUES(4,
-- (SELECT sum(price) FROM mydbtest.catalog INNER JOIN
-- 	(
--     SELECT catalog_id
--     FROM orders_catalog
--     WHERE order_id=4
-- 	) orders_catalog
--     ON catalog.id=orders_catalog.catalog_id
-- ), 4);

INSERT into orders values(0,0,0);




SELECT * FROM orders_catalog;
SELECT * FROM catalog;
SELECT * FROM orders;
SELECT * FROM categories;
SELECT * FROM statuses;
SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM type_hotel;
SELECT * FROM discount;


