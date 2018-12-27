package com.brianphiri.bookkeeper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository: BookRepository = BookRepository(application)

    private val bookList: LiveData<List<Book>>
    init {
        bookList = bookRepository.allBooks()
    }

    fun allBooks() : LiveData<List<Book>>{
        return bookList
    }

    fun insert(book: Book){
        bookRepository.insert(book)
    }

    fun update(book: Book){
        bookRepository.update(book)
    }

    fun delete(book: Book){
        bookRepository.delete(book)
    }
}