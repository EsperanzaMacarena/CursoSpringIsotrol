package com.lm2a.sb.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lm2a.sb.model.Ingredient;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JDBCIngredientRepository implements IngredientRepository {
    private final JdbcTemplate jdbc;

    @Override
    public List<Ingredient> findAll() {
        return jdbc.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject("SELECT id, name, type FROM Ingredient WHERE id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient (id, name, type) VALUES (?.?.?)", ingredient.getId(), ingredient.getName(),
                ingredient.getType().toString());

        return null;
    }

    private Ingredient mapRowToIngredient(ResultSet result, int rowNumberProcessing) throws SQLException {
        return new Ingredient(result.getString("id"), result.getString("name"),
                Ingredient.Type.valueOf(result.getString("type")));
    }

}
