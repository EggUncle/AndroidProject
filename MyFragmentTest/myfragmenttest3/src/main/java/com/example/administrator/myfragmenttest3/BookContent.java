package com.example.administrator.myfragmenttest3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 西域战神阿凡提 on 16.1.22.
 */
public class BookContent {

    public static class Book {
        public Integer id;
        public String title;
        public String desc;

        public Book(Integer id, String title, String desc) {
            this.id = id;
            this.title = title;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public static List<Book> ITEMS = new ArrayList<>();

    public static Map<Integer, Book> ITEM_MAP = new HashMap<>();

    static {
        addItem(new Book(1, "java", "i am java"));
        addItem(new Book(2, "android", "i am android"));
        addItem(new Book(3, "c++", "i am c++"));
    }

    private static void addItem(Book book) {
        ITEMS.add(book);
        ITEM_MAP.put(book.id, book);
    }

}