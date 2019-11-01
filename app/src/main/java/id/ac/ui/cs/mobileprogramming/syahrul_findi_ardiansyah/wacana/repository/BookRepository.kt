package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository

import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data.BookDao

class BookRepository private constructor(private val bookDao: BookDao) {

    fun getAllBooks() = bookDao.getAllBooks()

    fun getBook(bookId: Int) = bookDao.getBook(bookId)

    fun deleteAllBooks() = bookDao.deleteAllBooks()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: BookRepository? = null

        fun getInstance(bookDao: BookDao) =
            instance ?: synchronized(this) {
                instance ?: BookRepository(bookDao).also { instance = it }
            }
    }
}