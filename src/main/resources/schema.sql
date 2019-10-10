CREATE TABLE Ingredient
(
    id varchar(4) not null,
    name varchar(255) not null,
    ingredientType varchar(255) not null,
    primary key (id)
);

CREATE TABLE Taco(
    id identity,
    tacoName varchar(255) not null
);

CREATE TABLE TacoIngredientsCrossMap(
    id identity,
    tacoId varchar(255) not null,
    ingredientId varchar(4) not null,

    foreign key (tacoId) references Taco(id),
    foreign key (ingredientId) references Ingredient (id)
);

CREATE TABLE TacoOrder(
    id identity,
    tacoId bigint not null,
    placedAt datetime not null,

    name varchar(255) not null,
    street varchar(255) not null,
    city varchar(255) not null,
    state varchar(255) not null,
    ccNumber varchar(255) not null,

    foreign key (tacoId) references Taco(id)
);