CREATE TABLE city (
   id SERIAL PRIMARY KEY,
   name TEXT
);

ALTER TABLE candidate ADD COLUMN city_id INTEGER REFERENCES city(id);