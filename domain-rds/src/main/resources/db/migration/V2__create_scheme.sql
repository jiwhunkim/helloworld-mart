drop table if exists orders;
create table orders
(
    id                    bigint         not null auto_increment,
    account_id            bigint         not null,
    billing_amount        decimal(19, 2) not null,
    total_sales_amount    decimal(19, 2) not null,
    total_discount_amount decimal(19, 2) not null,
    total_amount          decimal(19, 2) not null,
    created_at            DATETIME(6) DEFAULT now(6) not null,
    created_by            VARCHAR(128)   NOT NULL DEFAULT '' not null,
    updated_at            DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by            VARCHAR(128)   NOT NULL DEFAULT '' not null,
    primary key (id)
) engine=InnoDB;

drop table if exists order_line_items;
create table order_line_items
(
    id                    bigint         not null auto_increment,
    order_id              bigint,
    product_id            bigint         not null,
    product_option_id     bigint         not null,
    seller_product_id     bigint         not null,
    sales_amount          decimal(19, 2) not null,
    discount_amount       decimal(19, 2) not null,
    amount                decimal(19, 2) not null,
    quantity              integer        not null,
    total_sales_amount    decimal(19, 2) not null,
    total_discount_amount decimal(19, 2) not null,
    total_amount          decimal(19, 2) not null,
    created_at            DATETIME(6) DEFAULT now(6) not null,
    created_by            VARCHAR(128)   NOT NULL DEFAULT '',
    updated_at            DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by            VARCHAR(128)   NOT NULL DEFAULT '',
    primary key (id)
) engine=InnoDB;

drop table if exists products;
create table products
(
    id          bigint       not null auto_increment,
    code        varchar(255),
    name        varchar(255),
    description varchar(255),
    created_at  DATETIME(6) DEFAULT now(6) not null,
    created_by  VARCHAR(128) NOT NULL DEFAULT '',
    updated_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by  VARCHAR(128) NOT NULL DEFAULT '',
    primary key (id)
) engine=InnoDB;

drop table if exists product_options;
create table product_options
(
    id                bigint         not null auto_increment,
    seller_product_id bigint         not null,
    code              varchar(128),
    name              varchar(255),
    description       varchar(255),
    sales_amount      decimal(19, 2) not null,
    discount_amount   decimal(19, 2) not null,
    amount            decimal(19, 2) not null,
    created_at        DATETIME(6) DEFAULT now(6) not null,
    created_by        VARCHAR(128)   NOT NULL DEFAULT '',
    updated_at        DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by        VARCHAR(128)   NOT NULL DEFAULT '',
    primary key (id)
) engine=InnoDB;

drop table if exists sellers;
create table sellers
(
    id         bigint       not null auto_increment,
    name       varchar(255),
    created_at DATETIME(6) DEFAULT now(6) not null,
    created_by VARCHAR(128) NOT NULL DEFAULT '',
    updated_at DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by VARCHAR(128) NOT NULL DEFAULT '',
    primary key (id)
) engine=InnoDB;

drop table if exists seller_products;
create table seller_products
(
    id              bigint         not null auto_increment,
    seller_id       bigint         not null,
    sku_id          bigint         not null,
    code            varchar(128),
    name            varchar(255),
    description     varchar(255),
    sales_amount    decimal(19, 2) not null,
    discount_amount decimal(19, 2) not null,
    amount          decimal(19, 2) not null,
    created_at      DATETIME(6) DEFAULT now(6) not null,
    created_by      VARCHAR(128)   NOT NULL DEFAULT '',
    updated_at      DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by      VARCHAR(128)   NOT NULL DEFAULT '',
    primary key (id)
) engine=InnoDB;

drop table if exists skus;
create table skus
(
    id           bigint         not null auto_increment,
    code         varchar(128),
    name         varchar(128),
    description  varchar(255),
    supply_price decimal(19, 2) not null,
    created_at   DATETIME(6) DEFAULT now(6) not null,
    created_by   VARCHAR(128)   NOT NULL DEFAULT '',
    updated_at   DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by   VARCHAR(128)   NOT NULL DEFAULT '',
    primary key (id)
) engine=InnoDB;

drop table if exists stocks;
create table stocks
(
    id                bigint       not null auto_increment,
    seller_product_id bigint       not null,
    quantity          integer      not null,
    reserved_quantity integer      not null,
    created_at        DATETIME(6) DEFAULT now(6) not null,
    created_by        VARCHAR(128) NOT NULL DEFAULT '',
    updated_at        DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) not null,
    updated_by        VARCHAR(128) NOT NULL DEFAULT '',
    primary key (id)
) engine=InnoDB