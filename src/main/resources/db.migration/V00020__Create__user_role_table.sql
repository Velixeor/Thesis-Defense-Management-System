create table vkr.user_role
(
    user_id integer not null,
    role_id integer not null,
    foreign key (user_id) references vkr.user (id)
        on delete cascade,
    foreign key (role_id) references vkr.role (id)
        on delete cascade
)
