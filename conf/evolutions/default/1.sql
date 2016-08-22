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

# --- !Downs

DROP TABLE placedorder;

# --- Placed Order schema

