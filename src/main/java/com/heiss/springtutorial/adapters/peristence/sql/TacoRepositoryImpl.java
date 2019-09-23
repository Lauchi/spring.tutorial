package com.heiss.springtutorial.adapters.peristence.sql;

import com.heiss.springtutorial.application.TacoRepository;
import com.heiss.springtutorial.domain.Taco;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Repository
public class TacoRepositoryImpl implements TacoRepository {

    @Autowired
    private JdbcTemplate database;

    @Override
    public Iterable<Taco> findAll() {
        var results = database.query(
                "Select * from Tacos join TacoIngredientsCrossMap on TacoIngredientsCrossMap.tacoId = Tacos.id",
                this::mapper);
        return results;
    }

    private Taco mapper(ResultSet rs, int i)
        throws SQLException {
        String tacoName = rs.getString("tacoName");
        UUID id = UUID.fromString(rs.getString("id"));
        ArrayList<String> ingredientIds = new ArrayList<>();

        do {
            String ingredientId = rs.getString("ingredientId");
            ingredientIds.add(ingredientId);
        } while (rs.next());

        return new Taco(id, null, tacoName, ingredientIds);
    }

    @Override
    public Taco findOne(UUID id) {
        var results = database.query(
                "Select * from Tacos " +
                        "join TacoIngredientsCrossMap on TacoIngredientsCrossMap.tacoId = Tacos.id " +
                "where Tacos.id = ?",
                this::mapper,
                id.toString());
        return results.size() == 0 ? null : results.get(0);
    }

    @Override
    public void save(Taco taco) {
        PreparedStatementCreator preparedStatementCreator =
                new PreparedStatementCreatorFactory(
                        "INSERT INTO Tacos (id, tacoName) values (?, ?)",
                        Types.VARCHAR, Types.VARCHAR)
                        .newPreparedStatementCreator(
                                Arrays.asList(
                                        taco.getId(),
                                        taco.getTacoName()
                                )
                        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        database.update(preparedStatementCreator, keyHolder);

        for (String in : taco.getTacoIngredients()) {
            PreparedStatementCreator psc =
                    new PreparedStatementCreatorFactory(
                            "INSERT INTO TacoIngredientsCrossMap (tacoId, ingredientId) VALUES (?, ?)",
                            Types.VARCHAR, Types.VARCHAR)
                            .newPreparedStatementCreator(
                                    Arrays.asList(
                                            taco.getId(),
                                            in
                                    )
                            );
            KeyHolder kh = new GeneratedKeyHolder();
            database.update(psc, kh);
        }
    }
}
