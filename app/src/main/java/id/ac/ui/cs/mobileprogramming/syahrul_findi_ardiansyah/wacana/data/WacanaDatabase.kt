package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class WacanaDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

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
                .addCallback(roomCallback).build()
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
        bookDao?.insert(Book("Title 1", "description 1", 100000))
        bookDao?.insert(Book("Title 2", "description 2", 110000))
        bookDao?.insert(Book("Title 3", "description 3", 120000))
    }
}