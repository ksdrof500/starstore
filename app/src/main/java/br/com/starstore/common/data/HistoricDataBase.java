package br.com.starstore.common.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.starstore.model.HistoricCart;

/**
 * Created by filipenunes on 1/2/18.
 */

@Database(entities = {HistoricCart.class}, version = 1, exportSchema = false)
public abstract class HistoricDataBase extends RoomDatabase {

    public abstract HistoricDao historicDao();
}
