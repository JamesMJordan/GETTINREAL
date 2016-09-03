# --- Placed Order Schema

# --- !Ups

CREATE TABLE placedorder (
    id bigserial NOT NULL PRIMARY KEY,
    name varchar(255),
    address1 varchar(255),
    address2 varchar(255),
    city varchar(255),
    state varchar(255),
    zipcode varchar(255)
);

CREATE TABLE account (
    id         bigserial NOT NULL PRIMARY KEY,
    email      varchar NOT NULL UNIQUE,
    password   varchar NOT NULL,
    name       varchar NOT NULL,
    role       varchar NOT NULL
);

CREATE TABLE pricing (
    id          serial NOT NULL PRIMARY KEY,
    materials   varchar NOT NULL,
    qty         varchar NULL,
    price       DECIMAL(5,2)
);


# --- !Downs

DROP TABLE placedorder;
DROP TABLE account;
DROP TABLE pricing;

