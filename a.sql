create table artist
(
    id        bigint auto_increment
        primary key,
    image_url varchar(255) null,
    name      varchar(255) null
);

create table school
(
    id        bigint auto_increment
        primary key,
    instagram varchar(255)      null,
    name      varchar(255)      null,
    location  varchar(255) null
);

create table festival
(
    end_date   date               null,
    start_date date               null,
    id         bigint auto_increment
        primary key,
    school_id  bigint             null,
    view_count bigint             not null,
    image_url  varchar(255)       null,
    season     varchar(255)       null,
    constraint FKliljndokbc32h70y0b6ex8t9i
        foreign key (school_id) references school (id)
);

create table festival_date_time
(
    festival_id bigint      null,
    id          bigint auto_increment
        primary key,
    start_time  datetime(6) null,
    constraint FKot996xukgic2ekrf67imrixl
        foreign key (festival_id) references festival (id)
);

create table line_up
(
    artist_id             bigint null,
    festival_date_time_id bigint null,
    id                    bigint auto_increment
        primary key,
    constraint FK40wq1awcc4xep7p7jh8jygk03
        foreign key (artist_id) references artist (id),
    constraint FKpkkn1r97knop7qyt6xbu72g2b
        foreign key (festival_date_time_id) references festival_date_time (id)
);

create table user
(
    id bigint auto_increment
        primary key
);

create table festival_like
(
    id          bigint auto_increment
        primary key,
    festival_id bigint null,
    user_id     bigint null,
    constraint FKgsmxk15b5ihdslqaxhpfcfp8k
        foreign key (user_id) references user (id),
    constraint FKsvsiclbp7p0jhuq5vmb3r14y
        foreign key (festival_id) references festival (id)
);