package com.brianphiri.bookkeeper

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = [Book::class], version = 1)
abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDao() :BookDao

    companion object {
        private var INSTANCE: BookRoomDatabase? = null
        fun getDatabase(context: Context): BookRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(BookRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            BookRoomDatabase::class.java, "book_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}