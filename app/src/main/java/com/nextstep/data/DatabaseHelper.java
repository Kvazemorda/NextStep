package com.nextstep.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nextstep.R;
import com.nextstep.entity.PersonEntity;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "nextStep.db";
    private static final int DATABASE_VERSOIN = 1;
    private Dao<PersonEntity, Integer> simpleDao = null;
    private RuntimeExceptionDao<PersonEntity, Integer> simpleRuntimeDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSOIN, R.raw.ormlite_config);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.i(DatabaseHelper.class.getName(), "onCreate");
        try {
            TableUtils.createTable(connectionSource, PersonEntity.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create data base", e);
            throw new RuntimeException(e);
        }
        RuntimeExceptionDao<PersonEntity, Integer> dao = getSimpleDataDao();
        PersonEntity personEntity = new PersonEntity("Valya", "ilyavanavara@mail.com");
        dao.create(personEntity);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, PersonEntity.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<PersonEntity, Integer> getSimpleDataDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(PersonEntity.class);
        }
        return simpleRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        simpleDao = null;
        simpleRuntimeDao = null;
    }
}
