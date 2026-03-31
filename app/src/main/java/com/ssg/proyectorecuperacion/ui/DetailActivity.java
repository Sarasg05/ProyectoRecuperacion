package com.ssg.proyectorecuperacion.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ssg.proyectorecuperacion.R;

public class DetailActivity extends AppCompatActivity {

    TextView title, author, year, category, status;
    ImageView image;

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

        // Recibir datos
        String t = getIntent().getStringExtra("title");
        String a = getIntent().getStringExtra("author");
        String y = getIntent().getStringExtra("year");
        String c = getIntent().getStringExtra("category");
        String s = getIntent().getStringExtra("status");

        // Mostrar datos
        title.setText(t);
        author.setText(a);
        year.setText(y);
        category.setText(c);
        status.setText(s);

    }
}