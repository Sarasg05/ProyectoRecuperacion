package com.ssg.proyectorecuperacion.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.ssg.proyectorecuperacion.R;
import com.ssg.proyectorecuperacion.model.FavoritesManager;

public class DetailActivity extends AppCompatActivity {

    TextView title, author, year, category, status;
    ImageView image;
    Button btnFavorite;

    FavoritesManager favoritesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.txtTitleDetail);
        author = findViewById(R.id.txtAuthorDetail);
        year = findViewById(R.id.txtYearDetail);
        category = findViewById(R.id.txtCategoryDetail);
        status = findViewById(R.id.txtStatusDetail);
        image = findViewById(R.id.imgBookDetail);
        btnFavorite = findViewById(R.id.btnFavorite);

        favoritesManager = new FavoritesManager(this);

        // Recibir datos
        String t = getIntent().getStringExtra("title");
        String a = getIntent().getStringExtra("author");
        String y = getIntent().getStringExtra("year");
        String c = getIntent().getStringExtra("category");
        String s = getIntent().getStringExtra("status");

        String cover = getIntent().getStringExtra("cover");
        if (cover != null){
            Glide.with(this).load(cover).into(image);
        }

        // Mostrar datos
        title.setText(t);
        author.setText(a);
        year.setText(y);
        category.setText(c);
        status.setText(s);

        String bookId = t + "-" + a;

        updateFavoriteButton(bookId);

        btnFavorite.setOnClickListener(v -> {
            if(favoritesManager.isFavorite(bookId)){
                favoritesManager.removeFavorite(bookId);
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            }else{
                favoritesManager.addFavorite(bookId);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
            updateFavoriteButton(bookId);
        });

    }

    private void updateFavoriteButton(String bookId){
        if(favoritesManager.isFavorite(bookId)){
            btnFavorite.setText("Remove from favorites");
        }else{
            btnFavorite.setText("Add to favorites");
        }
    }
}