delete from Categories;
delete from Countries;
delete from Trademarks;

insert into Categories (Name) values ('Овощи')
insert into Categories (Name) values ('Фрукты')
insert into Categories (Name) values ('Хлеб и кондитерские изделия')
insert into Categories (Name) values ('Молочные продукты')
insert into Categories (Name) values ('Рыба, морепродукты')
insert into Categories (Name) values ('Вода, напитки')

insert into Countries (Name) values ('Беларусь') -- овощи, фрукты
insert into Countries (Name) values ('Россия') -- овощи, фрукты
insert into Countries (Name) values ('Польша') -- овощи, фрукты
insert into Countries (Name) values ('Украина') -- хлеб
insert into Countries (Name) values ('Литва')
insert into Countries (Name) values ('Латвия')

insert into Trademarks (Name) values ('Дорорс') -- овощи
insert into Trademarks (Name) values ('Merci')
insert into Trademarks (Name) values ('Сула')
insert into Trademarks (Name) values ('Air') -- фрукты
insert into Trademarks (Name) values ('7 Days') -- хлеб и конд изделия
insert into Trademarks (Name) values ('BelVita')
insert into Trademarks (Name) values ('Oreo')
insert into Trademarks (Name) values ('Барни')
insert into Trademarks (Name) values ('28 копеек') -- молочные
insert into Trademarks (Name) values ('Actimel')
insert into Trademarks (Name) values ('7 морей') -- рыба
insert into Trademarks (Name) values ('ABC') -- напитки
insert into Trademarks (Name) values ('Bonaqua ')
