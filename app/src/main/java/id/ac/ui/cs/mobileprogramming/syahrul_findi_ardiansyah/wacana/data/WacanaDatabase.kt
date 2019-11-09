package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Transaction

@Database(entities = [Book::class, Cart::class, Transaction::class], version = 1, exportSchema = false)
abstract class WacanaDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun cartDao(): CartDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var instance: WacanaDatabase? = null

        fun getInstance(context: Context): WacanaDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WacanaDatabase {
            return Room.databaseBuilder(context, WacanaDatabase::class.java, "wacana-db")
                .allowMainThreadQueries().addCallback(roomCallback).build()
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }
}

class PopulateDbAsyncTask(db: WacanaDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val bookDao = db?.bookDao()

    override fun doInBackground(vararg p0: Unit?) {
        bookDao?.insert(Book("Sugar Run", "Mesha Maren", 100000, R.drawable.sugar_run))
        bookDao?.insert(Book("Making Things Happen", "Elizabeth Murphy", 110000, R.drawable.making_things_happen))
        bookDao?.insert(Book("Love Does", "Bob Goff", 120000, R.drawable.love_does))
        bookDao?.insert(Book("The Intelligent Investor", "Benjamin Graham", 130000, R.drawable.intelligent_investor))
    }
}