package com.example.taller.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taller.data.dao.CocheDao
import com.example.taller.entity.Coche

@Database(entities = [Coche::class], version = 1, exportSchema = false)
abstract class TallerDatabase : RoomDatabase() {
        abstract fun cocheDao(): CocheDao
    companion object {
        @Volatile
        private var Instance: TallerDatabase? = null

        fun getDatabase(context: Context): TallerDatabase {
            //context.deleteDatabase("Taller_database")
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context,
                    TallerDatabase::class.java,
                    "Taller_database"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}