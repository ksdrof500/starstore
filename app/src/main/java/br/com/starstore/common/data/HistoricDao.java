package br.com.starstore.common.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.starstore.model.HistoricCart;

/**
 * Created by filipenunes on 1/2/18.
 */
@Dao
public interface HistoricDao {

    @Query("SELECT * FROM historic ORDER BY date DESC")
    List<HistoricCart> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(HistoricCart historicCart);
}
