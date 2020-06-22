insert into car_brand(name) values ('Nissan');
insert into car_brand(name) values ('Chevrolet');
insert into car_brand(name) values ('Audi');

insert into car_class(name) values('Limo');
insert into car_class(name) values('Old timer');
insert into car_class(name) values('SUV');
insert into car_class(name) values('Minivan');
insert into car_class(name) values('Caravan');
insert into car_class(name) values('Cabriolet');

insert into car_model(name, car_brand_id, car_class_id) values('A7', 3, 6);
insert into car_model(name, car_brand_id, car_class_id) values('A6', 3, 5);
insert into car_model(name, car_brand_id, car_class_id) values('A4', 3, 5);
insert into car_model(name, car_brand_id, car_class_id) values('Aveo', 2, 6);
insert into car_model(name, car_brand_id, car_class_id) values('Versa', 1, 3);

insert into fuel_type(name) values('Gasoline');
insert into fuel_type(name) values('Diesel');
insert into fuel_type(name) values('Natural Gas');

insert into transmission(name) values('Manual');
insert into transmission(name) values('Automatic');
insert into transmission(name) values('Semiautomatic');



