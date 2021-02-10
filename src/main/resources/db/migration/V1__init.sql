create TABLE product (
                        id                  bigserial not null AUTO_INCREMENT,
                        name                varchar(200) not null,
                        price               float not null
);

create table users (
                       id                      bigserial primary key,
                       username                varchar(30) not null unique,
                       password                varchar(80) not null,
                       email                   varchar(50) unique,
                       first_name              varchar(200) not null,
                       last_name               varchar(200) not null,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp
);

create table roles (
                       id                      bigserial primary key,
                       name                    varchar(50) not null unique,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
                             user_id                 bigint not null references users (id),
                             role_id                 bigint not null references roles (id),
                             primary key (user_id, role_id)
);

create table order_items (
                             id                      bigserial primary key,
                             title                   varchar(255),
                             quantity                int,
                             price_per_item          int,
                             price                   int
);