package com.ssg.proyectorecuperacion.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ssg.proyectorecuperacion.R;
import com.ssg.proyectorecuperacion.adapter.BookAdapter;
import com.ssg.proyectorecuperacion.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookAdapter adapter;
    List<Book> listBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listBooks = new ArrayList<>();

        // Datos de prueba
        listBooks.add(new Book("Harry Potter", "J.K. Rowling", "1997", "Fantasía", "Leído"));
        listBooks.add(new Book("El Hobbit", "Tolkien", "1937", "Fantasía", "Pendiente"));
        listBooks.add(new Book("1984", "George Orwell", "1949", "Distopía", "Leído"));

        adapter = new BookAdapter(listBooks, this);
        recyclerView.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}