package com.heiss.springtutorial.adapters.peristence.sql;

import com.heiss.springtutorial.adapters.webapi.TacoOrder;
import com.heiss.springtutorial.application.OrderRepository;
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
import java.util.Arrays;
import java.util.UUID;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    private JdbcTemplate database;

    @Override
    public Taco findCurrentOrder() {
        return null;
    }

    @Override
    public void save(TacoOrder tacoOrder) {
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into TacoOrder (id, tacoId, placedAt, name, street, city, state, ccNumber ) values (?, ?, ?, ?, ?, ?, ?, ?)",
                        Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                tacoOrder.getId(),
                                tacoOrder.getTacoId(),
                                tacoOrder.getPlacedAt(),
                                tacoOrder.getName(),
                                tacoOrder.getStreet(),
                                tacoOrder.getCity(),
                                tacoOrder.getState(),
                                tacoOrder.getCcNumber()));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        database.update(psc, keyHolder);
    }

    @Override
    public Iterable<TacoOrder> getAll() {
        return database.query("Select * from TacoOrder", this::tacoOrderMapper);
    }

    private TacoOrder tacoOrderMapper(ResultSet resultSet, int i) throws SQLException {
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setId(UUID.fromString(resultSet.getString("id")));
        tacoOrder.setTacoId(UUID.fromString(resultSet.getString("tacoId")));

        tacoOrder.setCity(resultSet.getString("city"));
        tacoOrder.setName(resultSet.getString("name"));
        tacoOrder.setCcNumber(resultSet.getString("ccNumber"));
        tacoOrder.setState(resultSet.getString("state"));
        tacoOrder.setStreet(resultSet.getString("street"));

        return tacoOrder;
    }
}
