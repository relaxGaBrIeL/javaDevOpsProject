CREATE TABLE users (
                       id INT NOT NULL AUTO_INCREMENT,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id)
);

INSERT INTO users (username, password) VALUES ('admin', 'admin');

