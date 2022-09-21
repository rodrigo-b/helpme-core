---- DATABASE CREATION
CREATE DATABASE cs50;
USE cs50;

---- TABLES CREATION
CREATE TABLE user (
				   id_user INT AUTO_INCREMENT NOT NULL,
				   name VARCHAR(200) NOT NULL,
				   email VARCHAR(200) NOT NULL,
				   password VARCHAR(200) NOT NULL,
				   creation DATE NOT NULL,
				   CONSTRAINT roundemail_unique UNIQUE(email),
				   PRIMARY KEY(id_user)
				  );


CREATE TABLE improvement (
		id_improvement INT AUTO_INCREMENT NOT NULL,
		title VARCHAR(200) NOT NULL,
		message  TEXT NOT NULL,
		likes INT DEFAULT 0,
		classification VARCHAR(200),
		id_user INT NOT NULL,
		PRIMARY KEY(id_improvement),
		CONSTRAINT fk_user_improvement
		FOREIGN KEY(id_user) REFERENCES user(id_user)
);


CREATE TABLE role (
    id_role INT AUTO_INCREMENT NOT NULL,
    role_name VARCHAR(100) NOT NULL,
    PRIMARY KEY(id_role)
);

CREATE TABLE user_roles(
    id_user_role INT AUTO_INCREMENT NOT NULL,
    id_role INT NOT NULL,
    id_user INT NOT NULL,
    PRIMARY KEY (id_user_role),
    CONSTRAINT fk_id_user
    FOREIGN KEY(id_user) REFERENCES user(id_user),
    CONSTRAINT fk_id_role
    FOREIGN KEY(id_role) REFERENCES role(id_role)
);

---- INSERT TABLE
INSERT INTO user(name,email,password, creation) VALUES ('teste','teste@gmail.com', '$2a$12$DDv92dSxeStNqPmMvV0z3.R5R07MW5i6GgdD1snOtI40pDf0MdMJm', CURDATE());

INSERT INTO improvement (title,message, likes, classification, id_user) VALUES ('FINANCE BILLS','BILLS NOT SHOWED ', 60, 'FINANCIAL', 1);
INSERT INTO improvement (title,message, likes, classification, id_user) VALUES ('PLAY ROOM','PLAY ROOM CLOSED ', 10, 'LEISURE', 1);
INSERT INTO improvement (title,message, likes, classification, id_user) VALUES ('SWIMMING POOL','SWIMMING POOL CLOSED ', 15, 'INFRASTRUCTURE', 1);
INSERT INTO improvement (title,message, likes, classification, id_user) VALUES ('CAMERAS IN FRONT','DONT HAVE CAMERAS IN FRONT ', 20, 'SECURITY', 1);
INSERT INTO improvement (title,message, likes, classification, id_user) VALUES ('COMMUNICATION WARNINGS','IT IS NOT GOOD ', 30, 'COMMUNICATION', 1);
INSERT INTO improvement (title,message, likes, classification, id_user) VALUES ('CONDOMINIUM','C ', 35, 'FINANCIAL', 1);

INSERT INTO role (role_name) VALUES ('ROLE_USER');

INSERT INTO user_roles (id_role, id_user) VALUES (1,1);

---- SELECTS
SELECT classification, round((count(*) * 100.0 / sum(count(*)) Over() ),2) as 'total'
FROM improvement
GROUP BY classification;