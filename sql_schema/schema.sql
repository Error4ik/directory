CREATE DATABASE directory;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id       UUID PRIMARY KEY,
  email    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL
);

DROP TABLE IF EXISTS directories;
CREATE TABLE directories (
  id          UUID PRIMARY KEY,
  name        VARCHAR(255)  NOT NULL,
  description VARCHAR(2000) NOT NULL,
  user_id     UUID          NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS models;
CREATE TABLE models (
  id           UUID PRIMARY KEY,
  name         VARCHAR(255) NOT NULL,
  dir_id UUID,
  FOREIGN KEY (dir_id) REFERENCES directories (id)
);

DROP TABLE IF EXISTS properties;
CREATE TABLE properties (
  id       UUID PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  value    VARCHAR(255) NOT NULL,
  model_id UUID,
  FOREIGN KEY (model_id) REFERENCES models (id)
);

DROP TABLE IF EXISTS attributes;
CREATE TABLE attributes (
  id           UUID PRIMARY KEY,
  name         VARCHAR(255) NOT NULL,
  directory_id UUID,
  FOREIGN KEY (directory_id) REFERENCES directories (id)
)