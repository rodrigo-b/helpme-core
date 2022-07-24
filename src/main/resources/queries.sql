---- DATABASE CREATION
CREATE DATABASE cs50;
USE cs50;

---- TABLES CREATION
CREATE TABLE user (
				   id INT AUTO_INCREMENT NOT NULL,
				   name VARCHAR(200) NOT NULL,
				   email VARCHAR(200) NOT NULL,
				   password VARCHAR(200) NOT NULL,
				   creation DATE NOT NULL,
				   CONSTRAINT email_unique UNIQUE(email),
				   PRIMARY KEY(id)
				  );


CREATE TABLE improvement (
		id INT AUTO_INCREMENT NOT NULL,
		title VARCHAR(200) NOT NULL,
		message  TEXT NOT NULL,
		likes INT DEFAULT 0,
		classification VARCHAR(200),
		user_id INT NOT NULL,
		PRIMARY KEY(id),
		FOREIGN KEY(user_id) REFERENCES user(id)
);


---- INSERT TABLE
INSERT INTO user(name,email,uPassword, creation) VALUES ('teste','teste@gmail.com', 'teste', CURDATE());

