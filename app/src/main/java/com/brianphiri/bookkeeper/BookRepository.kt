package com.brianphiri.bookkeeper

import android.os.AsyncTask
import android.arch.lifecycle.LiveData
import android.app.Application



class BookRepository  internal constructor(application: Application) {
    private val bookDao: BookDao
    private var bookList: LiveData<List<Book>>
    init {
        val db = BookRoomDatabase.getDatabase(application)
        bookDao = db!!.bookDao()
        bookList = bookDao.allBooks()
    }

    fun allBooks() : LiveData<List<Book>> {
        return bookList
    }

    fun insert(book: Book) {
        InsertAsyncTask(bookDao).execute(book)
    }

    fun update(book: Book) {
        UpdateAsyncTask(bookDao).execute(book)
    }

    fun delete(book: Book){
        DeleteAsyncTask(bookDao).execute(book)
    }

    fun getBooksByNameOrAuthor(search : String): LiveData<List<Book>> {
        return bookDao.getBooksByNameOrAuthor(search)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: BookDao) :
        AsyncTask<Book, Void, Void>() {

        override fun doInBackground(vararg params: Book): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class UpdateAsyncTask internal constructor(private val mAsyncTaskDao: BookDao): AsyncTask<Book, Void, Void>(){
        override fun doInBackground(vararg params: Book): Void? {
            mAsyncTaskDao.update(params[0])
            return null
        }

    }

    private class DeleteAsyncTask internal constructor(private val mAsyncTaskDao: BookDao): AsyncTask<Book,Void,Void>(){
        override fun doInBackground(vararg params: Book): Void? {
            mAsyncTaskDao.delete(params[0])
            return null
        }

    }
}