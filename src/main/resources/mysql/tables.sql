DROP SCHEMA IF EXISTS escape_room;
CREATE SCHEMA IF NOT EXISTS escape_room;
USE escape_room;

CREATE TABLE escape_rooms (
id INT auto_increment PRIMARY KEY,
name VARCHAR(255) NOT NULL,
url VARCHAR(100) NOT NULL
);

CREATE TABLE themes (
id INT auto_increment PRIMARY KEY,
name VARCHAR(255) NOT NULL,
description VARCHAR (255) NOT NULL
);

CREATE TABLE deco (
id INT auto_increment PRIMARY KEY,
name VARCHAR(255) NOT NULL,
description VARCHAR(255) NOT NULL,
type ENUM('furniture', 'garnishment') NOT NULL,
theme_id INT NOT NULL,
escaperoom_id INT NOT NULL,
price DECIMAL (10,2) NOT NULL,
FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms(id),
FOREIGN KEY (theme_id) REFERENCES themes(id)
);

CREATE TABLE rooms (
id INT auto_increment PRIMARY KEY,
name VARCHAR(255) NOT NULL,
price DECIMAL (10,2) NOT NULL,
escaperoom_id INT NOT NULL,
FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms(id)
);

CREATE TABLE puzzles (
id INT auto_increment PRIMARY KEY,
name VARCHAR(255) NOT NULL,
difficulty ENUM ('easy', 'medium', 'hard') NOT NULL,
room_id  INT NOT NULL,
answer VARCHAR(255) NOT NULL,
story TEXT NOT NULL,
theme_id INT NOT NULL,
price DECIMAL (10,2) NOT NULL,
FOREIGN KEY (room_id) REFERENCES rooms(id),
FOREIGN KEY (theme_id) REFERENCES themes(id)
);

CREATE TABLE packs(
id INT auto_increment PRIMARY KEY,
name VARCHAR(255) NOT NULL
);

CREATE TABLE puzzle_packs(
name VARCHAR(255) NOT NULL,
puzzle_id INT,
pack_id INT,
PRIMARY KEY (puzzle_id, pack_id),
FOREIGN KEY (puzzle_id) REFERENCES puzzles(id),
FOREIGN KEY (pack_id) REFERENCES packs(id)
);

CREATE TABLE customers (
id INT auto_increment PRIMARY KEY,
name VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL
);

CREATE TABLE reservations (
id INT auto_increment PRIMARY KEY,
customer_id INT NOT NULL,
pack_id INT NOT NULL,
total_price DECIMAL (10,2),
creation_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
payment_date DATETIME NULL,
escaperoom_date DATETIME NOT NULL,
FOREIGN KEY (customer_id) REFERENCES customers(id),
FOREIGN KEY (pack_id) REFERENCES packs(id)
);

CREATE TABLE notifications (
id INT auto_increment PRIMARY KEY,
customer_id INT NOT NULL,
message TEXT NOT NULL,
FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE rewards (
id INT auto_increment PRIMARY KEY,
recipient VARCHAR(50) NOT NULL,
description VARCHAR(255) NOT NULL,
emision_date TIMESTAMP,
delivery_date TIMESTAMP
);
