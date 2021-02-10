CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP
);

CREATE TABLE candidate (
   id SERIAL PRIMARY KEY,
   name TEXT
);