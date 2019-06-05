insert into calc_users(id, name, last_name, email, password, is_active, role) values (1, 'admin', 'admin', 'admin@mail.com', '$2a$12$YgcnMmnG8htheT.6w1ukO.EwdTnJo0hUWdGzkFu3OPNRbT1O2QNxa', 1, 'SUPER_ADMIN');

insert into calc_companies (name) values ('Karotka');
insert into calc_users_job (role,company_id, user_id) values (1,1,1);
insert into companies_admin (user_id, company_id) values (1,1);

insert into calc_vegetables (name) values ("marchewka");
insert into calc_vegetables (name) values ("brokuł");
insert into calc_vegetables (name) values ("cebula kostka");
insert into calc_vegetables (name) values ("cebula plastry");
insert into calc_vegetables (name) values ("fasola szparagowa cała");
insert into calc_vegetables (name) values ("fasola szparagowa cięta");
insert into calc_vegetables (name) values ("fasola ziarno");
insert into calc_vegetables (name) values ("groszek");
insert into calc_vegetables (name) values ("kalafior");
insert into calc_vegetables (name) values ("szpinak całe liście");
insert into calc_vegetables (name) values ("szpinak rozdrobniony");

insert into calc_resources (name, gus) values ("gaz", "012");
insert into calc_resources (name, gus) values ("woda", "023");
insert into calc_resources (name, gus) values ("prąd", "024");
insert into calc_resources (name, gus) values ("węgiel", "060");
insert into calc_resources (name, gus) values ("olej", "064");
insert into calc_resources (name, gus) values ("benzyna", "088");

insert into calc_leftovers (name) values ("odpad");
insert into calc_leftovers (name) values ("odrzut");
insert into calc_leftovers (name) values ("przeklasyfikowanie");
insert into calc_leftovers (name) values ("zwrot");

insert into calc_lines (name, outsourced, used, company_id) values ("linia marchewka plasterki", TRUE, FALSE, NULL);

insert into calc_stages (name, outsourced, used, company_id) values ("etap marchewka plasterki przyjęcie surowca", TRUE, FALSE, NULL);
insert into calc_stages (name, outsourced, used, company_id) values ("etap marchewka plasterki selekcja", TRUE, FALSE, NULL);
insert into calc_stages (name, outsourced, used, company_id) values ("etap marchewka plasterki mycie", TRUE, FALSE, NULL);
insert into calc_stages (name, outsourced, used, company_id) values ("etap marchewka plasterki krojenie", TRUE, FALSE, NULL);
insert into calc_stages (name, outsourced, used, company_id) values ("etap marchewka plasterki zamrażanie", TRUE, FALSE, NULL);
insert into calc_stages (name, outsourced, used, company_id) values ("etap marchewka plasterki pakowanie", TRUE, FALSE, NULL);

insert into calc_modules (name, power, outsourced, used, company_id) values ("kosz zasypowy", "1.5", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("krajalnica", "7.5", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("obcinarka końcówek", "0.75", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("owijarka", "1.3", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("podajnik na tunel zamrażalniczy", "0.75", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("podajnik", "1.1", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("płuczka kaskadowa", "79.5", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("płuczka szczotkowa", "0.37", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("płuczka wibracyjna", "0.55", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("sorter", "4.5", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("taśma odbierająca odpad", "0.55", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("taśma odbierająca odrzut", "1.5", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("taśma odbierająca", "0.55", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("taśma transportowa", "0.75", TRUE, FALSE, NULL);
insert into calc_modules (name, power, outsourced, used, company_id) values ("tunel zamrażalniczy", "128.4", TRUE, FALSE, NULL);

insert into calc_module_vegetables (module_id, vegetable_id) values (1, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (2, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (3, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (4, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (5, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (6, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (7, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (8, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (9, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (10, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (11, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (12, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (13, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 1);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 2);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 3);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 4);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 5);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 6);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 7);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 8);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 9);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 10);
insert into calc_module_vegetables (module_id, vegetable_id) values (14, 11);
insert into calc_module_vegetables (module_id, vegetable_id) values (15, 1);

insert into calc_module_resources (module_id, resource_id) values (1, 3);
insert into calc_module_resources (module_id, resource_id) values (2, 3);
insert into calc_module_resources (module_id, resource_id) values (3, 3);
insert into calc_module_resources (module_id, resource_id) values (4, 3);
insert into calc_module_resources (module_id, resource_id) values (5, 3);
insert into calc_module_resources (module_id, resource_id) values (6, 3);
insert into calc_module_resources (module_id, resource_id) values (7, 2);
insert into calc_module_resources (module_id, resource_id) values (7, 3);
insert into calc_module_resources (module_id, resource_id) values (8, 2);
insert into calc_module_resources (module_id, resource_id) values (8, 3);
insert into calc_module_resources (module_id, resource_id) values (9, 2);
insert into calc_module_resources (module_id, resource_id) values (9, 3);
insert into calc_module_resources (module_id, resource_id) values (10, 3);
insert into calc_module_resources (module_id, resource_id) values (11, 3);
insert into calc_module_resources (module_id, resource_id) values (12, 3);
insert into calc_module_resources (module_id, resource_id) values (13, 3);
insert into calc_module_resources (module_id, resource_id) values (14, 3);
insert into calc_module_resources (module_id, resource_id) values (15, 3);

insert into calc_module_leftovers (module_id, leftover_id) values (11, 1);
insert into calc_module_leftovers (module_id, leftover_id) values (12, 2);

insert into calc_stage_modules (stage_id, module_id) values (1, 13);
insert into calc_stage_modules (stage_id, module_id) values (1, 1);
insert into calc_stage_modules (stage_id, module_id) values (2, 14);
insert into calc_stage_modules (stage_id, module_id) values (2, 10);
insert into calc_stage_modules (stage_id, module_id) values (2, 11);
insert into calc_stage_modules (stage_id, module_id) values (2, 12);
insert into calc_stage_modules (stage_id, module_id) values (3, 14);
insert into calc_stage_modules (stage_id, module_id) values (3, 7);
insert into calc_stage_modules (stage_id, module_id) values (3, 8);
insert into calc_stage_modules (stage_id, module_id) values (3, 9);
insert into calc_stage_modules (stage_id, module_id) values (4, 14);
insert into calc_stage_modules (stage_id, module_id) values (4, 3);
insert into calc_stage_modules (stage_id, module_id) values (4, 2);
insert into calc_stage_modules (stage_id, module_id) values (5, 14);
insert into calc_stage_modules (stage_id, module_id) values (5, 5);
insert into calc_stage_modules (stage_id, module_id) values (5, 15);
insert into calc_stage_modules (stage_id, module_id) values (6, 14);
insert into calc_stage_modules (stage_id, module_id) values (6, 6);
insert into calc_stage_modules (stage_id, module_id) values (6, 4);

insert into calc_line_stages (line_id, stage_id) values (1, 1);
insert into calc_line_stages (line_id, stage_id) values (1, 2);
insert into calc_line_stages (line_id, stage_id) values (1, 3);
insert into calc_line_stages (line_id, stage_id) values (1, 4);
insert into calc_line_stages (line_id, stage_id) values (1, 5);
insert into calc_line_stages (line_id, stage_id) values (1, 6);

insert into calc_lines (name, outsourced, used, company_id) values ("linia groszek umyty", FALSE, FALSE, 1);

insert into calc_stages (name, outsourced, used, company_id) values ("etap groszek umyty mycie", FALSE, FALSE, 1);

insert into calc_modules (name, power, outsourced, used, company_id) values ("troskliwe mycie", "1.5", FALSE, FALSE, 1);
insert into calc_modules (name, power, outsourced, used, company_id) values ("polerowanie na wysoki połysk", "7.5", FALSE, FALSE, 1);
insert into calc_modules (name, power, outsourced, used, company_id) values ("poprawki pod lupą", "0.75", FALSE, FALSE, 1);

insert into calc_module_vegetables (module_id, vegetable_id) values (16, 8);
insert into calc_module_vegetables (module_id, vegetable_id) values (17, 8);
insert into calc_module_vegetables (module_id, vegetable_id) values (18, 8);

insert into calc_module_vegetables (module_id, vegetable_id) values (13, 8);
insert into calc_module_vegetables (module_id, vegetable_id) values (1, 8);
insert into calc_module_vegetables (module_id, vegetable_id) values (6, 8);
insert into calc_module_vegetables (module_id, vegetable_id) values (4, 8);

insert into calc_module_resources (module_id, resource_id) values (16, 2);
insert into calc_module_resources (module_id, resource_id) values (16, 3);
insert into calc_module_resources (module_id, resource_id) values (17, 2);
insert into calc_module_resources (module_id, resource_id) values (17, 3);
insert into calc_module_resources (module_id, resource_id) values (18, 2);
insert into calc_module_resources (module_id, resource_id) values (18, 3);

insert into calc_stage_modules (stage_id, module_id) values (7, 16);
insert into calc_stage_modules (stage_id, module_id) values (7, 17);
insert into calc_stage_modules (stage_id, module_id) values (7, 18);

insert into calc_line_stages (line_id, stage_id) values (2, 1);
insert into calc_line_stages (line_id, stage_id) values (2, 7);
insert into calc_line_stages (line_id, stage_id) values (2, 6);