package com.example.jetpack.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.jetpack.entity.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    Long insertBook(Book book);

    @Query("select * from Book")
    List<Book> loadAllBooks();
}
