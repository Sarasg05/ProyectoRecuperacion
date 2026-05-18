package com.ssg.proyectorecuperacion.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.ssg.proyectorecuperacion.R;
import com.ssg.proyectorecuperacion.adapter.AuthorPagerAdapter;
import com.ssg.proyectorecuperacion.model.Book;
import com.ssg.proyectorecuperacion.model.BookResponse;
import com.ssg.proyectorecuperacion.network.ApiService;
import com.ssg.proyectorecuperacion.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorActivity extends AppCompatActivity {

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_author);

        viewPager = findViewById(R.id.authorViewPager);

        String author =
                getIntent().getStringExtra("author");

        ApiService apiService =
                RetrofitClient.getClient().create(ApiService.class);

        Call<BookResponse> call =
                apiService.getBooksByAuthor(author);

        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call,
                                   Response<BookResponse> response) {

                if (response.isSuccessful() &&
                        response.body() != null) {

                    ArrayList<Book> books =
                            new ArrayList<>(response.body().getDocs());

                    AuthorPagerAdapter adapter =
                            new AuthorPagerAdapter(books, AuthorActivity.this);

                    viewPager.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}