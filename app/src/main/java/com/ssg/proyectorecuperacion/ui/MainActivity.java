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
import com.ssg.proyectorecuperacion.model.BookResponse;
import com.ssg.proyectorecuperacion.network.ApiService;
import com.ssg.proyectorecuperacion.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        adapter = new BookAdapter(listBooks, this);
        recyclerView.setAdapter(adapter);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<BookResponse> call = apiService.searchBooks("harry potter");

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listBooks.clear();
                    listBooks.addAll(response.body().getDocs());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}