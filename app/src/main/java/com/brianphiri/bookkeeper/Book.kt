package com.brianphiri.bookkeeper

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "books")
class Book(@PrimaryKey
           val id : String,
           val author: String,
           val name: String) {
}