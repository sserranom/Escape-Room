DROP DATABASE IF EXISTS escape_room;

CREATE DATABASE escape_room;
USE escape_room;


CREATE TABLE escape_rooms
(
    id   INT auto_increment PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    url  VARCHAR(100)        NOT NULL
);

CREATE TABLE themes
(
    id            INT auto_increment PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255) NOT NULL,
    escaperoom_id INT          NOT NULL,
    FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms (id) ON DELETE CASCADE
);

CREATE TABLE rooms
(
    id         INT auto_increment PRIMARY KEY,
    name       VARCHAR(255)                    NOT NULL,
    price      DECIMAL(10, 2)                  NOT NULL,
    difficulty ENUM ('easy', 'medium', 'hard') NOT NULL,
    theme_id   INT                             NOT NULL,
    FOREIGN KEY (theme_id) REFERENCES themes (id) ON DELETE CASCADE
);

CREATE TABLE deco
(
    id            INT auto_increment PRIMARY KEY,
    name          VARCHAR(255)                      NOT NULL,
    description   VARCHAR(255)                      NOT NULL,
    type          ENUM ('furniture', 'garnishment') NOT NULL,
    escaperoom_id INT                               NOT NULL,
    price         DECIMAL(10, 2)                    NOT NULL,
    FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms (id) ON DELETE CASCADE
);

CREATE TABLE deco_inventory
(
    id            INT auto_increment PRIMARY KEY,
    escaperoom_id INT NOT NULL,
    deco_id       INT NOT NULL,
    room_id       INT,
    FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE SET NULL,
    FOREIGN KEY (escaperoom_id) REFERENCES escape_rooms (id),
    FOREIGN KEY (deco_id) REFERENCES deco (id)

);

CREATE TABLE puzzles
(
    id       INT auto_increment PRIMARY KEY,
    name     VARCHAR(255)   NOT NULL,
    theme_id INT            NOT NULL,
    room_id  INT,
    answer   VARCHAR(255)   NOT NULL,
    story    TEXT           NOT NULL,
    price    DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (room_id) REFERENCES rooms (id) ON DELETE SET NULL,
    FOREIGN KEY (theme_id) REFERENCES themes (id) ON DELETE CASCADE
);



CREATE TABLE customers
(
    id            INT auto_increment PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    is_subscribed BOOLEAN      NOT NULL
);

CREATE TABLE reservations
(
    id              INT auto_increment PRIMARY KEY,
    customer_id     INT,
    puzzle_id       INT,
    total_price     DECIMAL(10, 2),
    creation_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    completion_date DATETIME,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE SET NULL,
    FOREIGN KEY (puzzle_id) REFERENCES puzzles (id) ON DELETE SET NULL
);

