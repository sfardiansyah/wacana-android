package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Transaction

@Dao
interface TransactionDao {

    @Insert
    fun insert(trx: Transaction)

    @Query("SELECT COUNT(*) FROM transactions")
    fun getCount(): Int
}