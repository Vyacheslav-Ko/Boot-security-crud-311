CREATE TABLE if not exists users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL
);

-- Table: roles
CREATE TABLE if not exists roles (
  id   INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);


-- Table for mapping user and roles: user_roles
CREATE TABLE if not exists user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
);


-- Insert data
INSERT INTO users VALUES (1, 'Slava', '$2y$12$9Y8FVG/35cKyqFlLYnUOD.ZPkekXsIjFbjDCB3vMCYH4sL3ZKFkGK', 'bbb@bk.ru');
INSERT INTO users VALUES (2, 'Person', '$2y$12$9Y8FVG/35cKyqFlLYnUOD.ZPkekXsIjFbjDCB3vMCYH4sL3ZKFkGK', 'bbbf@bk.ru');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1, 2);
INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 1);