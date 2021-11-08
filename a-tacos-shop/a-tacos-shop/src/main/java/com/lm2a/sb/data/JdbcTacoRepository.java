package com.lm2a.sb.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lm2a.sb.model.Taco;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JdbcTacoRepository implements TacoRepository {
    private final JdbcTemplate jdbc;

    @Override
    public Taco save(Taco taco) {
        Long id = saveTaco(taco);
        taco.getIngredients().forEach(i -> {
            jdbc.update("INSERT INTO Taco_Ingredients (taco_id,ingredient_id)VALUES (?.?)", id, i.getId());
        });

        return null;
    }

    private Long saveTaco(Taco taco) {
        taco.setCreatedAt(new Date());

        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "INSERT INTO Taco (name, createdAt) VALUES (?,?)", Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(psc, kh);

        return kh.getKey().longValue();

    }

}
