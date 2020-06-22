insert into user_table(dtype, username, password, blocked, is_blocked, can_rate, can_rent) values('BASIC_USER','guest1','guest', 'false', 'false', 'false', 'false');
insert into user_table(dtype, username, password, blocked, is_blocked, can_rate, can_rent) values('BASIC_USER','guest2','guest', 'false', 'false', 'false', 'false');
insert into user_table(dtype, username, password, blocked, is_blocked, can_rate, can_rent) values('BASIC_USER','guest3','guest', 'false', 'false', 'false', 'false');

insert into message(receiver_id, sender_id, content, date_sent ) values(1, 2, 'Hello, how much is this car?', '2020-06-08 02:00:00');
insert into message(receiver_id, sender_id, content, date_sent ) values(1, 2, 'Hi, I am intrested in one of your ads.', '2020-07-08 02:00:00');
