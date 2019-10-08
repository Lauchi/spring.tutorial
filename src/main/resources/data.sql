delete from TacoIngredientsCrossMap;
delete from TacoOrder;
delete from Taco;
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