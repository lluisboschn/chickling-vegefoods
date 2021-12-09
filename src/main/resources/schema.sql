
/* CAL AFEGIR PASSWORD*/
DROP TABLE if exists consumer;
CREATE TABLE consumer
(
    id VARCHAR (256) PRIMARY KEY,
    name VARCHAR(256),
    secondName VARCHAR(256),
    email VARCHAR(256),
    password VARCHAR(70) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1
);


DROP TABLE if exists product;
CREATE TABLE product
(
    id VARCHAR (256) PRIMARY KEY,
    name VARCHAR (256),
    price numeric,
    measure_unit VARCHAR(256),
    provider VARCHAR(256),
    vat_type VARCHAR(256),
    category VARCHAR(256),
    initial_date DATE,
    day_of_week VARCHAR(256),
    num_of_periods NUMBER,
    period VARCHAR(256),
    image CLOB
);

DROP TABLE if exists productOrder;
CREATE TABLE productOrder
(
    id VARCHAR (256) PRIMARY KEY,
    email VARCHAR(256),
    dat_deliver DATE,
    close_date DATE,
    status VARCHAR(256),
    idSubs VARCHAR(256),
    productId VARCHAR(256),
    amount NUMBER,
    price NUMBER,
    date DATE,

    FOREIGN KEY (productId) REFERENCES product(id)
);

DROP TABLE if exists periodicity;
CREATE TABLE periodicity
(
    id VARCHAR (256) PRIMARY KEY,
    initialDate DATE,
    dayOfWeek int,
    numberOfPeriods int,
    period VARCHAR (256)
);

DROP TABLE if exists subscription;
CREATE TABLE subscription
(
    id VARCHAR (256) PRIMARY KEY,
    consumerMail VARCHAR(256),
    productId VARCHAR(256),
    amount numeric,
    price numeric,
    creation_date DATE,
    close_date DATE,
    consumerId VARCHAR (256),
    FOREIGN KEY (productId) REFERENCES product(id),
    FOREIGN KEY (consumerId) REFERENCES consumer(id)
);

DROP TABLE if EXISTS authorities;
CREATE TABLE authorities
(
    authority_id int(11)     NOT NULL AUTO_INCREMENT,
    username     varchar(45) NOT NULL,
    role         varchar(45) NOT NULL,
    PRIMARY KEY (authority_id),
    UNIQUE KEY uni_username_role (role,username),
    CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES consumer (email)
);






