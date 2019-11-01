package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book

@Dao
interface BookDao {

    @Insert
    fun insert(book: Book)

    @Query("DELETE FROM books")
    fun deleteAllBooks()

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBook(bookId: Int): LiveData<Book>
}