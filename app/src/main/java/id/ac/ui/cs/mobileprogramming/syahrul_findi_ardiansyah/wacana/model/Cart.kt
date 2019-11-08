package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model

import androidx.room.*

@Entity(tableName = "carts", primaryKeys = ["id", "transactionId"])
data class Cart(
    @Embedded(prefix = "book_") var book: Book,
    var transactionId: Int,
    val count: Int = 1,
    val id: Int = book.id
)
