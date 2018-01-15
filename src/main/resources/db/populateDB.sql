DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name, address, phone, website, state) VALUES
  ('Corsar', 'Rehov Onyon 1, Ashdod, Israel', '+972 8-855-5090', 'https://corsar.com', 'STATE_ACTIVE'),
  ('Pinta', '1 Ha Ha-Banaim, Ashdod 7760901, Israel', '+972 8-856-6069', 'https://pinta.com', 'STATE_ACTIVE'),
  ('Kira', 'Givat Yona 4, Yair Stern, Ashdod 7722611, Israel', '+972 8-856-1632', 'https://kira.com', 'STATE_DISABLED');

INSERT INTO dishes (name, price, description, state, restaurant_id) VALUES
  ('Cheeseburger', 30, 'Cheeseburger Ingredients', 'STATE_ACTIVE', 100002),
  ('French fries', 15, 'French fries Ingredients', 'STATE_ACTIVE', 100002),
  ('Hamburger', 30, 'Hamburger Ingredients', 'STATE_REMOVED', 100002),
  ('Hot dog', 25, 'Hot Dog Ingredients', 'STATE_ACTIVE', 100003),
  ('Pasta', 50, 'Pasta Ingredients', 'STATE_ACTIVE', 100003),
  ('Sandwich', 20, 'Sandwich Ingredients', 'STATE_REMOVED', 100003);

INSERT INTO votes (user_id, restaurant_id, registered_date, registered_time) VALUES
  (100000, 100002, CURRENT_DATE, CURRENT_TIMESTAMP),
  (100000, 100003, CURRENT_DATE, CURRENT_TIMESTAMP),
  (100000, 100002, CURRENT_DATE + '1' DAY, CURRENT_TIMESTAMP  + '1' DAY),
  (100000, 100003, CURRENT_DATE + '1' DAY, CURRENT_TIMESTAMP  + '1' DAY);