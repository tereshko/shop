insert into product (name, price)
values
    ('milk', 10),
    ('water', 11),
    ('meat', 15),
    ('ice', 1),
    ('tv', 4),
    ('snack', 2),
    ('hotdog', 56),
    ('chease creme', 4),
    ('sea food', 55),
    ('pizza', 7),
    ('apple', 4),
    ('garlick', 10),
    ('rice', 5)
;

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email, first_name, last_name)
values
('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com', 'Anton', 'Tereshko'),
('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com', 'Test', 'Test');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);