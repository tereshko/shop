create table products (
                          id                      bigserial primary key,
                          title                   varchar(255),
                          price                   double
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

create table orders_address (
                                id                      bigserial primary key,
                                address1                varchar(255),
                                address2                varchar(255),
                                city                    varchar(255),
                                state                   varchar(255),
                                zip                     int
);

create table orders (
                        id                      bigserial primary key,
                        owner_id                bigint references users (id),
                        price                   int,
                        address_id              bigint references orders_address (id),
                        created_at              timestamp default current_timestamp,
                        updated_at              timestamp default current_timestamp
);



create table order_items (
                             id                      bigserial primary key,
                             order_id                bigint references orders (id),
                             product_id              bigint references products (id),
                             title                   varchar(255),
                             quantity                int,
                             price_per_product       int,
                             price                   int,
                             created_at              timestamp default current_timestamp,
                             updated_at              timestamp default current_timestamp
);




