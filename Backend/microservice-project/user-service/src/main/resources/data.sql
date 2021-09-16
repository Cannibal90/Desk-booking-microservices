-- USER DETAILS
INSERT INTO my_user_details(age, city, name, surname) VALUES (44, 'Lodz', 'Jan', 'Nowak');
INSERT INTO my_user_details(age, city, name, surname) VALUES (50, 'Warszawa', 'Piotr', 'Kowalski');
INSERT INTO my_user_details(age, city, name, surname) VALUES (32, 'Lodz', 'Marek', 'Piatek');
INSERT INTO my_user_details(age, city, name, surname) VALUES (38, 'Krakow', 'Tomasz', 'Dzienny');
INSERT INTO my_user_details(age, city, name, surname) VALUES (25, 'Lodz', 'User', 'user');
INSERT INTO my_user_details(age, city, name, surname) VALUES (25, 'Lodz', 'Admin', 'admin');

-- USER
INSERT INTO my_users (email, password, role, username, user_details_id) VALUES ('nowak.jan@gmail.com', '$2a$10$qoj4vCez9ovjH1R/JE745eiWghn8vBflFmH3wLXPv3Xb98AmgKNEu', 'ROLE_ADMIN', 'nowak', 1);
INSERT INTO my_users (email, password, role, username, user_details_id) VALUES ('kowalski.piotr@gmail.com', '$2a$10$YzUXqaHVWM/1U1KyTUr7qeUFnLinp/7rKemSFmH4ydJgvjxBUjYmm', 'ROLE_ADMIN', 'kowalski', 2);
INSERT INTO my_users (email, password, role, username, user_details_id) VALUES ('piatek.marek@gmail.com', '$2a$10$Xx4z.Km8QGdTbBa7oocbQ.FbAbuQScpvCEHLcDXGTw.SL4EqO6982', 'ROLE_ADMIN', 'piatek', 3);
INSERT INTO my_users (email, password, role, username, user_details_id) VALUES ('dzienny.tomasz@gmail.com', '$2a$10$CeSoBIHnjSPyTPP6/isa1enmIQ.aOVVkGLbzF57EhHxakal.A1WOO', 'ROLE_ADMIN', 'dzienny', 4);
INSERT INTO my_users (email, password, role, username, user_details_id) VALUES ('user.user@gmail.com', '$2a$10$1Ie/51rMb6KYWRVdr7X6yOKmd1m.wDa1XDZnlWmtClGY7sEaT73de', 'ROLE_USER', 'user', 5);
INSERT INTO my_users (email, password, role, username, user_details_id) VALUES ('admin.admin@gmail.com', '$2a$10$8rCx4t5EFdgFiGjy2/zpnut6WfsR/1gJXjTF.ZYAIF2R2sffxxlBS', 'ROLE_ADMIN', 'admin', 6);