INSERT INTO calc_users(id, name, last_name, email, password, is_active) values(1, 'Kamil', 'Klimek', 'admin@example.com', 'admin', TRUE);
INSERT INTO calc_users(id, name, last_name, email, password, is_active) values(2, 'Jan', 'Kowalski', 'jan@example.com', 'admin', TRUE);

INSERT INTO calc_roles(id, name, description) values(1, 'USER', 'Użytkownik aplikacji');
INSERT INTO calc_roles(id, name, description) values(2, 'SUPER_ADMIN', 'Admin zarządzający użytkownikami');

