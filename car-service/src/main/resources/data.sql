insert into location(city) values('Brcko');
insert into location(city) values('Novi Sad');

insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (1, 1, 1, 1, 1, 'true', 'true', 1, 2, 2500.45, 123.45, 333.33, 33.33, 1);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (2, 2, 2, 2, 2, 'true', 'true', 2, 1, 453.55, 122.55, 787.88, 33.33, 1);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (1, 1, 1, 1, 1, 'true', 'true', 1, 2, 2500.45, 123.45, 333.33, 33.33, 2);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (1, 1, 1, 1, 1, 'true', 'true', 1, 2, 2500.45, 123.45, 333.33, 33.33, 2);

insert into car_calendar(car_id) values (1);
insert into rental(car_calendar_id, start_date, end_date) values (1, '2020-05-05', '2020-06-10');

insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 3.1, 1, 1, 'Odlicno!', 0);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 4.1, 2, 2, 'Sve najbolje!', 0);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 1.2, 3, 4, 'Psovka', 0);
insert into car_rating(rating, user_id, car_id, comment, rating_status) values ( 3.7, 1, 3, 'Sve pohvale za vozilo!', 0);
