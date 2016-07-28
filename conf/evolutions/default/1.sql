# --- Placed Order Schema

# --- !Ups

CREATE TABLE placedOrder (
    id bigserial NOT NULL,
    name varchar(255),
    address1 varchar(255),
    address2 varchar(255),
    city varchar(255),
    state varchar(255),
    zipcode varchar(255),
    CONSTRAINT person_pkey PRIMARY KEY (id)

);

# --- !Downs

DROP TABLE placedOrder;