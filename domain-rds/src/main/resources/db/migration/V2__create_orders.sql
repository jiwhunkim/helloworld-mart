drop table if exists orders;
create table orders (
    id bigint not null auto_increment,
    primary key (id)
) engine=InnoDB;