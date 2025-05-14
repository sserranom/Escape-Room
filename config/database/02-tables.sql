DROP DATABASE IF EXISTS escape_room;

CREATE DATABASE escape_room;
USE escape_room;


CREATE TABLE escape_rooms
(
    id   INT auto_increment PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    url  VARCHAR(100) NOT NULL
);

CREATE TABLE themes
(
    id          INT auto_increment PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    escaperoom_id INT NOT NULL,
    FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms (id) ON DELETE CASCADE
);

CREATE TABLE deco
(
    id            INT auto_increment PRIMARY KEY,
    name          VARCHAR(255)                      NOT NULL,
    description   VARCHAR(255)                      NOT NULL,
    type          ENUM ('furniture', 'garnishment') NOT NULL,
    theme_id      INT                               NOT NULL,
    escaperoom_id INT                               NOT NULL,
    price         DECIMAL(10, 2)                    NOT NULL,
    FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms (id) ON DELETE CASCADE,
    FOREIGN KEY (theme_id) REFERENCES themes (id) ON DELETE CASCADE
);

CREATE TABLE rooms
(
    id            INT auto_increment PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    price         DECIMAL(10, 2) NOT NULL,
    escaperoom_id INT            NOT NULL,
    FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms (id) ON DELETE CASCADE
);

CREATE TABLE puzzles
(
    id         INT auto_increment PRIMARY KEY,
    name       VARCHAR(255)                    NOT NULL,
    difficulty ENUM ('easy', 'medium', 'hard') NOT NULL,
    room_id    INT                             NOT NULL,
    answer     VARCHAR(255)                    NOT NULL,
    story      TEXT                            NOT NULL,
    theme_id   INT                             NOT NULL,
    price      DECIMAL(10, 2)                  NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE CASCADE,
    FOREIGN KEY (theme_id) REFERENCES themes (id) ON DELETE CASCADE
);

CREATE TABLE packs
(
    id   INT auto_increment PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE puzzle_packs
(
    name      VARCHAR(255) NOT NULL,
    puzzle_id INT,
    pack_id   INT,
    PRIMARY KEY (puzzle_id, pack_id),
    FOREIGN KEY (puzzle_id) REFERENCES puzzles (id) ON DELETE CASCADE,
    FOREIGN KEY (pack_id) REFERENCES packs (id) ON DELETE CASCADE
);

CREATE TABLE customers
(
    id    INT auto_increment PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE reservations
(
    id              INT auto_increment PRIMARY KEY,
    customer_id     INT      ,
    pack_id         INT      NOT NULL,
    total_price     DECIMAL(10, 2),
    creation_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    payment_date    DATETIME NULL,
    escaperoom_date DATETIME NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE SET NULL,
    FOREIGN KEY (pack_id) REFERENCES packs (id) ON DELETE CASCADE
);

CREATE TABLE notifications
(
    id          INT auto_increment PRIMARY KEY,
    customer_id INT  NOT NULL,
    message     TEXT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE TABLE rewards
(
    id            INT auto_increment PRIMARY KEY,
    recipient     VARCHAR(50)  NOT NULL,
    description   VARCHAR(255) NOT NULL,
    emission_date TIMESTAMP,
    delivery_date TIMESTAMP
);
