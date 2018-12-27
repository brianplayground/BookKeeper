package com.brianphiri.bookkeeper

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Query("SELECT * FROM books")
    fun allBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE name LIKE :searchString OR author LIKE :searchString")
    fun getBooksByNameOrAuthor(searchString: String): LiveData<List<Book>>

    @Update
    fun update(book: Book)

    @Delete
    fun delete(book: Book)
}