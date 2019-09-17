package com.heiss.springtutorial.adapters.peristence.memory;

import com.heiss.springtutorial.application.IIngredientRepository;
import com.heiss.springtutorial.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

@Repository
public class IngredientRepository implements IIngredientRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Iterable<Ingredient> byType(Ingredient.IngredientType ingredientType) {
        return jdbc.query("select id, name, ingredientType from Ingredient where ingredientType=?",
                this::mapRowToIngredient, ingredientType.toString());
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query("select id, name, ingredientType from Ingredient",
                this::mapRowToIngredient);
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
            throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.IngredientType.valueOf(rs.getString("ingredientType")));
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject(
                "select id, name, ingredientType from Ingredient where id=?",
                this::mapRowToIngredient, id);
    }

    @Override
    public void save(Ingredient ingredient) {
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Ingredient (id, name, ingredientType) values (?, ?, ?)",
                        Types.NVARCHAR, Types.NVARCHAR, Types.NVARCHAR
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                ingredient.getId(),
                                ingredient.getName(),
                                ingredient.getIngredientType().toString()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
    }
}
