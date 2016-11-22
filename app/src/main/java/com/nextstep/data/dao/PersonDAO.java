package com.nextstep.data.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.nextstep.entity.PersonEntity;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO extends BaseDaoImpl<PersonEntity, Integer> {

    public PersonDAO(ConnectionSource connectionSource, Class<PersonEntity> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<PersonEntity> getAllPerson() throws SQLException{
        return this.queryForAll();
    }
}
