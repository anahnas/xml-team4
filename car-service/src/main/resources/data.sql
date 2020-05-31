insert into location(city) values('Brcko');
insert into location(city) values('Novi Sad');

insert into car(car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id)
values (1, 1, 1, 1, 1, 0, 1);

insert into car_calendar(car_id) values (1);
insert into rental(car_calendar_id, start_date, end_date) values (1, '2020-05-05', '2020-06-10');

