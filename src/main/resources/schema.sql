
create table if not exists Products(
Id int primary key,
Name nvarchar(max) not null,
TrademarkId int not null,
CountryId int not null,
CategoryId int not null,
Price decimal(18,3) not null,
Image_ref nvarchar(max) null
)

go

create table if not exists Categories(
Id int primary key,
Name nvarchar(max) not null
)
go

create table if not exists Countries(
Id int primary key,
Name nvarchar(max) not null
)

go

create table if not exists Trademarks(
Id int primary key,
Name nvarchar(max) not null
)

go

create table if not exists Users(
Id int primary key,
Name nvarchar(max) not null,
Surname nvarchar(max) not null,
Address nvarchar(max) not null,
Email nvarchar(max) not null,
Phone nvarchar(max) not null,
Password nvarchar(max) not null
)

go

create table if not exists Administrators(
Id int primary key,
Name nvarchar(max) not null,
Surname nvarchar(max) not null,
Email nvarchar(max) not null,
Password nvarchar(max) not null
)

go

create table if not exists Orders(
Id int primary key,
ClientId int not null,
Date date not null,
Time time not null,
PlacedAt timestamp not null,
State nvarchar(max)
)
go

create table if not exists Orders_Products(
OrderId int not null,
ProductId int not null,
Count int not null
)

go

alter table Products
add foreign key (CountryId) references Countries(Id);
alter table Products
add foreign key (CategoryId) references Categories(Id)
alter table Products
add foreign key (TrademarkId) references Trademarks(Id)

go

alter table Orders
add foreign key (ClientId) references Users(Id);
alter table Orders_Products
add foreign key (OrderId) references Orders(Id);
alter table Orders_Products
add foreign key (ProductId) references Products(Id)
