package com.example.pau.daomodule.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Pau on 30/01/2015.
 */



public class RestaurantORMHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private Dao<RestaurantORMDao, Integer> restaurantDao;

    public RestaurantORMHelper(Context context) {
        super(context, "RESTAURANTS_ORM.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.i("RestaurantORMHelper", "onCreate");
            TableUtils.createTable(connectionSource, RestaurantORMDao.class);
        }catch (SQLException e) {
            Log.e("ORM ERROR", "onCreate Error");
            throw new RuntimeException();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
        try {
            TableUtils.dropTable(connectionSource, RestaurantORMDao.class, true);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Dao<RestaurantORMDao, Integer> getDao() throws SQLException {
        restaurantDao = super.getDao(RestaurantORMDao.class);
        return restaurantDao;
    }
}

