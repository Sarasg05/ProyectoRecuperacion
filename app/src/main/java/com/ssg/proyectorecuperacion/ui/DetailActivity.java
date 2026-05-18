package com.ssg.proyectorecuperacion.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.ssg.proyectorecuperacion.R;
import com.ssg.proyectorecuperacion.adapter.DetailPagerAdapter;
import com.ssg.proyectorecuperacion.model.Book;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        viewPager = findViewById(R.id.viewPager);

        ArrayList<Book> books =
                (ArrayList<Book>) getIntent().getSerializableExtra("books");

        int position =
                getIntent().getIntExtra("position", 0);

        DetailPagerAdapter adapter =
                new DetailPagerAdapter(books, this);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(position, false);
    }
}