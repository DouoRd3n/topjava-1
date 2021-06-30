DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2015-06-01 21:00', 'админ ужын', 1500, 100001),
       ('2015-06-01 12:00', 'админ обед', 530 , 100001 ),
       ('2021-06-29 9:10' , 'юзер сніданок ', 1000, 100000),
       ('2021-06-29 14:00', 'обед юзер', 500, 100000 ),
       ('2021-06-29 18:00', 'ужын юзер', 500 , 100000 ),
       ('2021-06-30 9:00', 'юзер сніданок', 1100 , 100000),
       ('2021-06-30 14:00', 'юзер обед', 500 , 100000),
       ('2021-06-30 19:30', 'юзер ужын', 500 , 100000);