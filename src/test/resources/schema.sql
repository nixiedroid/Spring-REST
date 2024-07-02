CREATE SCHEMA site;
CREATE TABLE site.coffees (
                              id bigserial PRIMARY KEY,
                              name varchar(100) NOT NULL,
                              has_milk boolean default false,
                              created timestamp default now()
);
INSERT INTO site.coffees (id,name,has_milk) values (1,'Cappucino',true);
INSERT INTO site.coffees (id,name) values (2,'Amerricano');
INSERT INTO site.coffees (id,name,has_milk) values (3,'Latte',true);
INSERT INTO site.coffees (id,name) values (4,'Moka');
select * from site.coffees;

CREATE TABLE site.users (
                            id bigserial PRIMARY KEY,
                            first_name varchar(100) NOT NULL,
                            last_name varchar(100) NOT NULL,
                            created timestamp default now() NOT NULL
);
INSERT INTO site.users (first_name,last_name) values ('Susie', 'McElory');
INSERT INTO site.users (first_name,last_name) values ('Nonna', 'Igonet');
INSERT INTO site.users (first_name,last_name) values ('Lester', 'Bowering');
INSERT INTO site.users (first_name,last_name) values ('Boardinng', 'Reaver');
select * from site.users;

CREATE TABLE site.favourite_coffees (
                                        id bigserial PRIMARY KEY,
                                        user_id bigint REFERENCES site.users(id),
                                        coffee_id bigint REFERENCES site.coffees(id)
);

INSERT INTO site.favourite_coffees (user_id,coffee_id) values (1,1);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (1,2);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (2,3);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (3,1);
INSERT INTO site.favourite_coffees (user_id,coffee_id) values (4,4);

select
    u.first_name as username,
    c.name as "likes coffee"
from site.users u
         join site.favourite_coffees f on u.id = f.user_id
         join site.coffees c on f.coffee_id = c.id
order by u.first_name desc;