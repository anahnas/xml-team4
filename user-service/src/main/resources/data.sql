insert into user_table(dtype, username, password, blocked, is_blocked, can_rate, can_rent) values
('BASIC_USER','guest1@gmail.com','guest1', 0, 0, 0, 0);
insert into user_table(dtype, username, password, blocked, is_blocked, can_rate, can_rent) values
('BASIC_USER','guest2@gmail.com','guest2', 0, 0, 0, 0);
insert into user_table(dtype, username, password, blocked, is_blocked, can_rate, can_rent) values
('BASIC_USER','guest3@gmail.com','guest3', 0, 0, 0, 0);


insert into user_table(dtype, username, password, blocked) values
('ADMIN','admin','admin', 0);
insert into user_table(dtype, username, password, blocked) values
('ADMIN','admin1','admin1', 0);


insert into user_table(dtype, username, password, blocked, name, last_name, agency_name, address, business_id) values
('AGENT','agent1','agent1', 0 ,'Zarko','Zrenjanin','21 decembar','Novaka Radonjica 21.','1');
insert into user_table(dtype, username, password, blocked, name, last_name, agency_name, address, business_id) values
('AGENT','agent2','agent2', 0 ,'Nikola','Nikolic','Lily','Bulevar Evrope 5.','2');
insert into user_table(dtype, username, password, blocked, name, last_name, agency_name, address, business_id) values
('AGENT','agent3','agent3', 0 ,'Marko','Mirkovic','Ana','Hadzi Ruvimova 33.','3');



insert into message(receiver_id, sender_id, content, date_sent ) values(1, 2, 'Hello, how much is this car?', '2020-06-08 02:00:00');
insert into message(receiver_id, sender_id, content, date_sent ) values(1, 2, 'Hi, I am intrested in one of your ads.', '2020-07-08 02:00:00');
insert into message(receiver_id, sender_id, content, date_sent ) values(1, 3, 'Hi...', '2020-07-08 02:00:00');
