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
import com.ssg.proyectorecuperacion.model.BookDetail;
import com.ssg.proyectorecuperacion.model.FavoritesManager;
import com.ssg.proyectorecuperacion.network.ApiService;
import com.ssg.proyectorecuperacion.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    TextView title, author, year, category, status, description;
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
        description = findViewById(R.id.txtDescriptionDetail);
        image = findViewById(R.id.imgBookDetail);
        btnFavorite = findViewById(R.id.btnFavorite);

        favoritesManager = new FavoritesManager(this);

        String workId = getIntent().getStringExtra("workId");

        String cover = getIntent().getStringExtra("cover");

        if (cover != null){
            Glide.with(this).load(cover).into(image);
        }

        if (workId != null) {

            ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
            Call<BookDetail> call = apiService.getBookDetail(workId);

            call.enqueue(new Callback<BookDetail>() {
                @Override
                public void onResponse(Call<BookDetail> call, Response<BookDetail> response) {
                    if (response.isSuccessful() && response.body() != null) {

                        BookDetail book = response.body();

                        title.setText(book.getTitle());
                        description.setText(book.getDescription());
                        year.setText(book.getYear());

                        author.setText("Autor desconocido");
                        category.setText("-");
                        status.setText("-");
                    }
                }

                @Override
                public void onFailure(Call<BookDetail> call, Throwable t) {
                    t.printStackTrace();
                }
            });

            updateFavoriteButton(workId);

            btnFavorite.setOnClickListener(v -> {
                if(favoritesManager.isFavorite(workId)){
                    favoritesManager.removeFavorite(workId);
                    Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                }else{
                    favoritesManager.addFavorite(workId);
                    Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                }
                updateFavoriteButton(workId);
            });
        }
    }

    private void updateFavoriteButton(String workId){
        if(favoritesManager.isFavorite(workId)){
            btnFavorite.setText("Remove from favorites");
        }else{
            btnFavorite.setText("Add to favorites");
        }
    }
}