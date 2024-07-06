INSERT INTO users (email, username, password, birth_date, role) VALUES ('john@mail.com', 'John-user', '$2a$10$CJgEoobU2gm0euD4ygru4ukBf9g8fYnPrMvYk.q0GMfOcIDtUhEwC', '2003-07-12', 'ROLE_ADMIN');
INSERT INTO users (email, username, password, birth_date, role) VALUES ('hannah@mail.com', 'Hannah-user', '$2a$10$yYQaJrHzjOgD5wWCyelp0e1Yv1KEKeqUlYfLZQ1OQvyUrnEcX/rOy', '1999-04-23', 'ROLE_USER');
INSERT INTO users (email, username, password, birth_date, role) VALUES ('tom@mail.com', 'Tom-user', '$2a$10$CdEJ2PKXgUCIwU4pDQWICuiPjxb1lysoX7jrN.Y4MTMoY9pjfPALO', '2000-11-07', 'ROLE_USER');

INSERT INTO ingredients (name) VALUES ('flour');
INSERT INTO ingredients (name) VALUES ('egg');
INSERT INTO ingredients (name) VALUES ('sugar');
INSERT INTO ingredients (name) VALUES ('butter');
INSERT INTO ingredients (name) VALUES ('vanilla essence');
INSERT INTO ingredients (name) VALUES ('chocolate');
INSERT INTO ingredients (name) VALUES ('salt');
INSERT INTO ingredients (name) VALUES ('cocoa powder');
INSERT INTO ingredients (name) VALUES ('peanut butter');
INSERT INTO ingredients (name) VALUES ('milk');
INSERT INTO ingredients (name) VALUES ('baking powder');


INSERT INTO recipes (name, cooking_time_in_minutes, author_id) VALUES ('sponge cake', 50, 1);
INSERT INTO recipes (name, cooking_time_in_minutes, author_id) VALUES ('brownie', 40, 1);
INSERT INTO recipes (name, cooking_time_in_minutes, author_id) VALUES ('peanut cookies', 30, 2);
INSERT INTO recipes (name, cooking_time_in_minutes, author_id) VALUES ('cupcakes', 30, 2);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (1, 1, 'GRAMS', 74);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (1, 2, 'PIECES', 3);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (1, 3, 'GRAMS', 74);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (1, 4, 'GRAMS', 21);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (1, 5, 'GRAMS', 5);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 1, 'GRAMS', 140);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 2, 'PIECES', 3);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 3, 'GRAMS', 300);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 4, 'GRAMS', 115);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 5, 'GRAMS', 5);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 6, 'GRAMS', 200);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 7, 'GRAMS', 2);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (2, 8, 'GRAMS', 30);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (3, 1, 'GRAMS', 260);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (3, 2, 'PIECES', 1);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (3, 3, 'GRAMS', 100);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (3, 4, 'GRAMS', 120);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (3, 5, 'GRAMS', 5);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (3, 7, 'GRAMS', 2);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (3, 9, 'GRAMS', 80);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (4, 1, 'GRAMS', 60);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (4, 2, 'PIECES', 1);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (4, 3, 'GRAMS', 90);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (4, 4, 'GRAMS', 60);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (4, 8, 'GRAMS', 30);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (4, 10, 'GRAMS', 65);
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, measure_unit, amount) VALUES (4, 11, 'GRAMS', 2);

INSERT INTO recipe_cooking_steps (recipe_id, steps) VALUES (1, '["Mix eggs and sugar with mixer till it come white","Add flour and mix gently with silicone spatula","Add melted butter and mix gently with silicone spatula","Lay out dough into baking pan and bake at 165°C for 30 minutes","After taking sponge out of the oven give it shock by hit to cooking surface","Let it cool"]');
INSERT INTO recipe_cooking_steps (recipe_id, steps) VALUES (2, '["Mix eggs with salt and vanilla essence","In another bowl melt chocolate on water bath, not all sugar may not dissolve, its okay","Add butter and sugar to chocolate and mix with silicone spatula","Add chocolate mix to egg mix and mix them together with silicone spatula","Add flour and cocoa powder and mix them gently with silicone spatula","Lay out dough into baking pan and bake at 180°C for 30 minutes","Let it cool"]');
INSERT INTO recipe_cooking_steps (recipe_id, steps) VALUES (3, '["Mix soft butter and sugar","Add egg, salt, vanilla essence and mix it","Add peanut butter and flour and mix it","Roll out dough in height about 1sm and cut in any form you prefer","Lay out into baking pan with parchment","Bake at 180°C for 10-12 minutes","Let it cool"]');
INSERT INTO recipe_cooking_steps (recipe_id, steps) VALUES (4, '["Mix soft butter, sugar and egg with mixer","Add milk and mix again","Add flour and cocoa powder and mix again","Add baking powder and mix with spatula","Grease baking molds with butter","Lay out dough into molds","Bake at 180°C for 20 minutes","Let it cool"]');

INSERT INTO user_followers (user_id, follower_id) VALUES (1, 2);
INSERT INTO user_followers (user_id, follower_id) VALUES (1, 3);
INSERT INTO user_followers (user_id, follower_id) VALUES (2, 3);

INSERT INTO liked_recipes (user_id, recipe_id) VALUES (2, 1);
INSERT INTO liked_recipes (user_id, recipe_id) VALUES (2, 2);
INSERT INTO liked_recipes (user_id, recipe_id) VALUES (3, 1);
INSERT INTO liked_recipes (user_id, recipe_id) VALUES (3, 2);
INSERT INTO liked_recipes (user_id, recipe_id) VALUES (3, 3);

INSERT INTO saved_recipes (user_id, recipe_id) VALUES (1, 1);
INSERT INTO saved_recipes (user_id, recipe_id) VALUES (1, 3);
INSERT INTO saved_recipes (user_id, recipe_id) VALUES (2, 1);
INSERT INTO saved_recipes (user_id, recipe_id) VALUES (3, 4);
INSERT INTO saved_recipes (user_id, recipe_id) VALUES (3, 2);