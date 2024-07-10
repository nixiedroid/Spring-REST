```postgresql
CREATE SCHEMA "site";
CREATE TABLE site.coffees (
id bigserial PRIMARY KEY,
name varchar(100) NOT NULL,
has_milk boolean default false,
created timestamp default now(),
uid uuid NOT NULL
);
INSERT INTO site.coffees (name,has_milk,uid) values ('Cappucino',true,'227f9922-f3ae-4167-bd6e-1f69820dca4c');
INSERT INTO site.coffees (name,uid) values ('Amerricano','3e2e1b99-2305-47e7-ac9e-21b681fec7f7');
INSERT INTO site.coffees (name,has_milk,uid) values ('Latte',true,'96bd4668-c8b1-4260-b813-e27415968402');
INSERT INTO site.coffees (name,uid) values ('Moka','ec61f219-52eb-4b54-8e61-3abc12271f65');
select * from site.coffees;

CREATE TABLE site.users (
id bigserial PRIMARY KEY,
first_name varchar(100) NOT NULL,
last_name varchar(100) NOT NULL,
created timestamp default now() NOT NULL,
uid uuid NOT NULL
);
INSERT INTO site.users (first_name,last_name,uid) values ('Susie', 'McElory','925db921-0ace-49a6-9286-edb8fbc9efd1');
INSERT INTO site.users (first_name,last_name,uid) values ('Nonna', 'Igonet','1e8431e1-4b80-4339-a0aa-f811711e908e');
INSERT INTO site.users (first_name,last_name,uid) values ('Lester', 'Bowering','03f9e2d6-c1ab-4ff3-9518-7abd364ba5d3');
INSERT INTO site.users (first_name,last_name,uid) values ('Boardinng', 'Reaver','229f8b8b-c31e-470f-8b4d-09230df3aadb');
select * from site.users;

CREATE TABLE site.favourite_coffees (
id bigserial PRIMARY KEY,
user_id bigint REFERENCES site.users(id),
coffee_id bigint REFERENCES site.coffees(id)
);

INSERT INTO site.favourite_coffees (user_id,coffee_id) values (1,1);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (1,2);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (1,7);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (2,3);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (3,1);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (4,4);
INSERT INTO site.favourite_coffees (id,user_id,coffee_id) values (3,1,3);

select
u.first_name as username,
c.name as "likes coffee"
from site.users u
join site.favourite_coffees f on u.id = f.user_id
join site.coffees c on f.coffee_id = c.id
order by u.first_name desc;

```