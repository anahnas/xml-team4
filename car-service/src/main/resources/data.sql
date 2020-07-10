insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, waiver_price_per_day, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (3, 1, 1, 1, 1, 'true', 5, 'true', 1, 2, 15, 0.1, 400, 230, 1);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, waiver_price_per_day, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (3, 2, 2, 2, 2, 'false', 0, 'true', 2, 1, 35, 0.2, 350, 1130, 1);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, waiver_price_per_day, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (1, 5, 5, 1, 1, 'true', 8, 'true', 1, 2, 30, 0.2, 300, 140, 2);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, waiver_price_per_day, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (2, 4, 6, 1, 1, 'false', 0, 'true', 3, 2, 25, 0.4, 450, 85, 2);

insert into car_calendar(car_id) values (1);
insert into rental(car_calendar_id, start_date, end_date) values (1, '2020-07-09', '2020-07-12');

insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 3.1, 1, 1, 'Very good!', 0);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 3.1, 1, 1, 'Amazing cooperation!', 1);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 3.1, 1, 2, 'Amazing cooperation!', 1);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 4.1, 2, 2, 'All the best!', 0);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 1.2, 3, 4, 'Swear word', 0);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 3.7, 1, 3, 'Only words of praise for the vehicle!', 0);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 3.1, 1, 4, 'Amazing!', 1);

