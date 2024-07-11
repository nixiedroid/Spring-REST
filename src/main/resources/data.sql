INSERT INTO site.coffees (name, has_milk)
values ('Cappucino', true);
INSERT INTO site.coffees (name)
values ('Amerricano');
INSERT INTO site.coffees (name, has_milk)
values ('Latte', true);
INSERT INTO site.coffees (name)
values ('Moka');
INSERT INTO site.users (first_name, last_name)
values ('Susie', 'McElory');
INSERT INTO site.users (first_name, last_name)
values ('Nonna', 'Igonet');
INSERT INTO site.users (first_name, last_name)
values ('Lester', 'Bowering');
INSERT INTO site.users (first_name, last_name)
values ('Boardinng', 'Reaver');
INSERT INTO site.favourite_coffees (user_id, coffee_id)
values (1, 1);
INSERT INTO site.favourite_coffees (user_id, coffee_id)
values (1, 2);
INSERT INTO site.favourite_coffees (user_id, coffee_id)
values (2, 3);
INSERT INTO site.favourite_coffees (user_id, coffee_id)
values (3, 1);
INSERT INTO site.favourite_coffees (user_id, coffee_id)
values (4, 4);
select u.first_name as username, c.name as "likes coffee"
from site.users u
         join site.favourite_coffees f on u.id = f.user_id
         join site.coffees c on f.coffee_id = c.id order by u.first_name desc;