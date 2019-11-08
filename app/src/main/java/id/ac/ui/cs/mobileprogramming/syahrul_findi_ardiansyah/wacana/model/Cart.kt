package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model

import androidx.room.*

@Entity(tableName = "carts")
data class Cart(
    @Embedded(prefix = "book_") var book: Book,
    val count: Int = 1,
    @PrimaryKey(autoGenerate = true) val id: Int = book.id
)
