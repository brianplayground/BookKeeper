package com.brianphiri.bookkeeper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class BookListAdapter(private val context: Context, private val onDeleteClickListener: OnDeleteClickListener) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    interface OnDeleteClickListener {
        fun OnDeleteClickListener(book: Book)
    }

    private var bookList : List<Book> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BookViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)
        return BookViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(p0: BookViewHolder, p1: Int) {
        val book = bookList[p1]
        p0.setData(book, p1)
        p0.setListeners()
    }

    fun setBooks(books: List<Book>) {
        bookList = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var pos: Int = 0
        fun setData(book: Book, position: Int){
            itemView.tvAuthor.text = book.author
            itemView.tvBook.text = book.name
            this.pos = position
        }


        fun setListeners(){
            itemView.ivRowEdit.setOnClickListener {
                val intent = Intent(context, EditBookActivity::class.java)
                intent.putExtra("id", bookList[pos].id)
                intent.putExtra("book", bookList[pos].name)
                intent.putExtra("author", bookList[pos].author)
                (context as Activity).startActivityForResult(intent, MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE)
            }
            itemView.ivRowDelete.setOnClickListener {
                onDeleteClickListener.OnDeleteClickListener(bookList[pos])
            }
        }
    }
}