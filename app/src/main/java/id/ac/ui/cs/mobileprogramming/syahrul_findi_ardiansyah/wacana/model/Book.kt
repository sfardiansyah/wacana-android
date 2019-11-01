package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    val title: String,
    val author: String,
    val price: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}