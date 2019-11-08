package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model

import androidx.room.*

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "modified_at") val modifiedAt: Long
)

data class TransactionWithCartItems(
    @Embedded val transaction: Transaction
) {

    @Relation(parentColumn = "id",  entityColumn = "transactionId", entity = Cart::class)
    val cartItems: List<Cart> = emptyList()
}