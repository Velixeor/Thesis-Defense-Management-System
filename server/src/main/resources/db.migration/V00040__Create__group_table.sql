create table vkr.group
(
    id                integer primary key generated by default as identity,
    name              text    not null unique,
    principal_user_id integer not null,
    foreign key (principal_user_id) references vkr.user (id)
        on delete cascade

)
