package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.IndonesiaCurrency
import java.math.BigDecimal

@Entity(tableName = "books")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class Book(
    val title: String,
    val author: String,
    val price: Long,
    val imgSrc: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun priceText() = IndonesiaCurrency.valueOf(BigDecimal(price))
}