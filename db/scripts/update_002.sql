CREATE TABLE photo (
   id SERIAL PRIMARY KEY,
   name TEXT not null,
   candidate_id int REFERENCES candidate(id) ON DELETE CASCADE
);