create table users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);
create table authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);

INSERT INTO users (username, password, enabled)
VALUES ('noman', '{bcrypt}$2a$12$RrkhRrfi8sY.wD.V0llRLO7EJ8N8mIhKb0lwWZtwkBtHIZvs2Wv4u', '1');
-- n12@noman
INSERT INTO authorities (username, authority)
VALUES ('noman', 'user');


INSERT INTO users (username, password, enabled)
VALUES ('lolo', '{noop}no23m@n@65', '1');
INSERT INTO authorities (username, authority)
VALUES ('lolo', 'admin');

CREATE TABLE `customer`
(
    `id`    int NOT NULL AUTO_INCREMENT,
    `email` varchar(255) DEFAULT NULL,
    `pwd`   varchar(255) DEFAULT NULL,
    `role`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `customer` (email, pwd, role)
VALUES ('lolo', '{noop}no23m@n@65', 'admin'),
       ('noman', '{bcrypt}$2a$12$RrkhRrfi8sY.wD.V0llRLO7EJ8N8mIhKb0lwWZtwkBtHIZvs2Wv4u', 'user');
-- n12@noman