CREATE TABLE Ingredients
(
  id  varchar(4) not null,
  name varchar(255) not null,
  ingredientType varchar(255) not null,
  primary key (id)
);

delete from Ingredients;
insert into Ingredients (id, name, ingredientType)
values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredients (id, name, ingredientType)
values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredients (id, name, ingredientType)
values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredients (id, name, ingredientType)
values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredients (id, name, ingredientType)
values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredients (id, name, ingredientType)
values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredients (id, name, ingredientType)
values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredients (id, name, ingredientType)
values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredients (id, name, ingredientType)
values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredients (id, name, ingredientType)
values ('SRCR', 'Sour Cream', 'SAUCE');

CREATE TABLE Tacos(
   id varchar(255) not null,
   tacoName varchar(255) not null,
   primary key (id)
);

CREATE TABLE TacoIngredientsCrossMap(
 id bigint not null auto_increment,
 tacoId varchar(255) not null,
 ingredientId varchar(4) not null,

 primary key (id),

 foreign key (tacoId) references Tacos(id),
 foreign key (ingredientId) references Ingredients (id)
);