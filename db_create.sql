DROP TABLE IF EXISTS film CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS film_entity CASCADE;
DROP TABLE IF EXISTS film_record CASCADE;

CREATE TABLE film(
  id SERIAL PRIMARY KEY,
  name text,
  author text,
  company text,
  year INTEGER,
  cassete_price numeric,
  disk_price numeric
);

CREATE TABLE person(
  id SERIAL PRIMARY KEY,
  name text,
  phone text,
  address text
);

CREATE TABLE film_entity(
  id SERIAL PRIMARY KEY,
  film_id INTEGER REFERENCES film (id) ON DELETE CASCADE,
  carrier_type INTEGER,
  is_vacant BOOLEAN
);

CREATE TABLE film_record(
  id SERIAL PRIMARY KEY,
  id_person INTEGER REFERENCES person (id) ON DELETE CASCADE,
  id_film_entity INTEGER REFERENCES film_entity (id) ON DELETE CASCADE,
  date_given timestamp,
  date_recieved timestamp,
  price numeric
);
