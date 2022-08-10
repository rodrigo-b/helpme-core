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

INSERT INTO improvement (title,message, likes, classification, user_id) VALUES ('FINANCE BILLS','BILLS NOT SHOWED ', 60, 'FINANCIAL', 1);
INSERT INTO improvement (title,message, likes, classification, user_id) VALUES ('PLAY ROOM','PLAY ROOM CLOSED ', 10, 'LEISURE', 1);
INSERT INTO improvement (title,message, likes, classification, user_id) VALUES ('SWIMMING POOL','SWIMMING POOL CLOSED ', 15, 'INFRASTRUCTURE', 1);
INSERT INTO improvement (title,message, likes, classification, user_id) VALUES ('CAMERAS IN FRONT','DONT HAVE CAMERAS IN FRONT ', 20, 'SECURITY', 1);
INSERT INTO improvement (title,message, likes, classification, user_id) VALUES ('COMMUNICATION WARNINGS','IT IS NOT GOOD ', 30, 'COMMUNICATION', 1);
INSERT INTO improvement (title,message, likes, classification, user_id) VALUES ('CONDOMINIUM','C ', 35, 'FINANCIAL', 1);



---- SELECTS
SELECT classification, round((count(*) * 100.0 / sum(count(*)) Over() ),2) as 'total'
FROM improvement
GROUP BY classification;