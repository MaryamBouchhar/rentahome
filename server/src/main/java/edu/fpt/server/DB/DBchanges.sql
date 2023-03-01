-- Path: src\main\java\edu\fpt\server\DB\DBchanges.sql

-- Client table

CREATE TABLE client
(
    id         INT          NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    phone      VARCHAR(20),
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Admin table

CREATE TABLE admin
(
    id         INT          NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Location table

CREATE TABLE location
(
    id         INT          NOT NULL AUTO_INCREMENT,
    address    VARCHAR(255) NOT NULL,
    longitude  DOUBLE       NOT NULL,
    latitude   DOUBLE       NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Property table

CREATE TABLE property
(
    id              INT          NOT NULL AUTO_INCREMENT,
    category        ENUM('Appartment', 'Studio', 'Villa', 'Duplex') NOT NULL DEFAULT 'Appartment',
    description     VARCHAR(255) NOT NULL,
    price           DOUBLE       NOT NULL,
    area            DOUBLE       NOT NULL,
    status          VARCHAR(255) NOT NULL,
    location        INT          NOT NULL,
    rent_type       ENUM('Daily', 'Monthly') NOT NULL DEFAULT 'Monthly',
    batthroom_count INT NOT NULL,
    room_count      INT NOT NULL,
    is_equipped     BOOLEAN      NOT NULL DEFAULT FALSE,
    publish_date    DATE         NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Property image table

CREATE TABLE property_image
(
    id         INT          NOT NULL AUTO_INCREMENT,
    property   INT          NOT NULL,
    image_path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Reservation table

CREATE TABLE reservation
(
    id         INT          NOT NULL AUTO_INCREMENT,
    client     INT          NOT NULL,
    property   INT          NOT NULL,
    start_date DATE         NOT NULL,
    end_date   DATE         NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

-- Property image table