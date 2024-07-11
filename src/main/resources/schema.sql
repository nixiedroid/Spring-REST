CREATE SCHEMA IF NOT EXISTS site;
CREATE TABLE site.coffees
(
    id       bigserial PRIMARY KEY,
    name     varchar(100) NOT NULL,
    has_milk boolean   default false,
    created  timestamp default now(),
    uid      uuid         NOT NULL
);

CREATE TABLE site.users
(
    id         bigserial PRIMARY KEY,
    first_name varchar(100)            NOT NULL,
    last_name  varchar(100)            NOT NULL,
    created    timestamp default now() NOT NULL,
    uid        uuid                    NOT NULL
);

CREATE TABLE site.favourite_coffees
(
    id        bigserial PRIMARY KEY,
    user_id   bigint REFERENCES site.users (id),
    coffee_id bigint REFERENCES site.coffees (id)
);

