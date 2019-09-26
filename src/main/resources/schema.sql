CREATE TABLE Ingredient
(
  id  varchar(4) not null,
  name varchar(255) not null,
  ingredientType varchar(255) not null,
  primary key (id)
);

delete from Ingredient;
insert into Ingredient (id, name, ingredientType)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, ingredientType)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, ingredientType)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, ingredientType)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, ingredientType)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, ingredientType)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, ingredientType)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, ingredientType)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, ingredientType)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, ingredientType)
values ('SRCR', 'Sour Cream', 'SAUCE');

CREATE TABLE Taco(
   id varchar(255) not null,
   tacoName varchar(255) not null,
   primary key (id)
);

CREATE TABLE TacoIngredientsCrossMap(
 id bigint not null auto_increment,
 tacoId varchar(255) not null,
 ingredientId varchar(4) not null,

 primary key (id),

 foreign key (tacoId) references Taco(id),
 foreign key (ingredientId) references Ingredient (id)
);

CREATE TABLE TacoOrder(
  id varchar(255) not null,
  tacoId varchar(255) not null,
  placedAt datetime not null,

  name varchar(255) not null,
  street varchar(255) not null,
  city varchar(255) not null,
  state varchar(255) not null,
  ccNumber varchar(255) not null,

  primary key (id),

  foreign key (tacoId) references Taco(id)
);