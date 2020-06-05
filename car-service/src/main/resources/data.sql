insert into location(city) values('Brcko');
insert into location(city) values('Novi Sad');
insert into location(city) values('Vlasenica');


insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id, image_id)
values (1, 1, 1, 1, 1, 'true', 'true', 1, 2, 2500.45, 123.45, 333.33, 33.33, 1, 1);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id, image_id)
values (2, 2, 2, 2, 2, 'true', 'true', 2, 1, 453.55, 122.55, 787.88, 33.33, 1, 2);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (1, 1, 1, 1, 1, 'true', 'true', 1, 2, 2500.45, 123.45, 333.33, 33.33, 2);
insert into car(car_brand_id, car_model_id, car_class_id, fuel_type_id, transmission_id, waiver, limited_kms, location_id, available_child_seats, price_per_day, price_per_km, limit_kms_per_day, kmage, owner_id)
values (1, 1, 1, 1, 1, 'true', 'true', 1, 2, 2500.45, 123.45, 333.33, 33.33, 2);

insert into advertisement(car_id, advertiser_id, start_date, end_date) values (1, 1, '2020-05-05', '2020-07-07');
insert into advertisement(car_id, advertiser_id, start_date, end_date) values (2, 1, '2020-05-05', '2020-07-07');

insert into car_calendar(car_id) values (1);
insert into rental(car_calendar_id, start_date, end_date) values (1, '2020-05-05', '2020-06-10');

--insert into slikica(image_path) values ('https://www.lamborghini.com/sites/it-en/files/DAM/lamborghini/facelift_2019/homepage/families-gallery/mobile/Aventador_SVJ_cc-verde_alceo-Leirion_Forged_20_21_Shiny_Black-red_caliper-sceneplate_env.png')
--insert into slikica(image_path) values ('https://upload.wikimedia.org/wikipedia/commons/e/e7/Lamborghini_Aventador_Genf_2018.jpg');
