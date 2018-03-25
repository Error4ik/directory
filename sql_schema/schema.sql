CREATE TABLE users (
  id       SERIAL PRIMARY KEY,
  email    VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255)        NOT NULL
);

CREATE TABLE directories (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(255)  NOT NULL,
  description VARCHAR(2000) NOT NULL,
  user_id     INTEGER       NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE models (
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(255) NOT NULL,
  directory_id INTEGER,
  FOREIGN KEY (directory_id) REFERENCES directories (id)
);

CREATE TABLE properties (
  id       SERIAL PRIMARY KEY,
  name     VARCHAR(255) NOT NULL,
  value    VARCHAR(255) NOT NULL,
  model_id INTEGER,
  FOREIGN KEY (model_id) REFERENCES models (id)
);