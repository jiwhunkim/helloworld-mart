drop table if exists orders;
create table orders
(
    id bigint not null auto_increment,
    primary key (id)
) engine=InnoDB;

drop table if exists order_line_items;
create table order_line_items
(
    id       bigint not null auto_increment,
    order_id bigint,
    primary key (id)
) engine=InnoDB;

drop table if exists products;
create table products
(
    id          bigint not null auto_increment,
    code        varchar(255),
    name        varchar(255),
    description varchar(255),
    primary key (id)
) engine=InnoDB;

drop table if exists product_options;
create table product_options
(
    id                bigint not null auto_increment,
    seller_product_id bigint not null,
    code              varchar(128),
    name              varchar(255),
    description       varchar(255),
    primary key (id)
) engine=InnoDB;
drop table if exists sellers;
create table sellers
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine=InnoDB;

drop table if exists seller_products;
create table seller_products
(
    id          bigint not null auto_increment,
    seller_id   bigint not null,
    sku_id      bigint not null,
    code        varchar(128),
    name        varchar(255),
    description varchar(255),
    primary key (id)
) engine=InnoDB;

drop table if exists skus;
create table skus
(
    id          bigint not null auto_increment,
    code        varchar(128),
    name        varchar(128),
    description varchar(255),
    primary key (id)
) engine=InnoDB;

drop table if exists stocks;
create table stocks
(
    id                bigint  not null auto_increment,
    seller_product_id bigint  not null,
    quantity          integer not null,
    reserved_quantity integer not null,
    primary key (id)
) engine=InnoDB