CREATE TABLE users
(
  username text,
  password text,
  id integer NOT NULL,
  CONSTRAINT user_id PRIMARY KEY (id)
)

insert into users(id, username, password) values(101, 'foo', 'bar')