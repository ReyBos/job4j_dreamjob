CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   name TEXT not null,
   email TEXT not null,
   password TEXT not null
);