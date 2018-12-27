package com.brianphiri.bookkeeper

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val bookRepository: BookRepository = BookRepository(application)

    fun update(book: Book) {
        bookRepository.update(book)
    }

    fun delete(book: Book) {
        bookRepository.delete(book)
    }

    fun getBooksByNameOrAuthor(search: String): LiveData<List<Book>> {
        return bookRepository.getBooksByNameOrAuthor(search)
    }

}