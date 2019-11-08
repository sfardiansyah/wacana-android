package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@Entity(tableName = "books")
@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
data class Book(
    val title: String,
    val author: String,
    val price: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}