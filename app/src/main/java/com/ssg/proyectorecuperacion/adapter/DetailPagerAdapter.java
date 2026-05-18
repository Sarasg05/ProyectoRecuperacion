package com.ssg.proyectorecuperacion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ssg.proyectorecuperacion.R;
import com.ssg.proyectorecuperacion.model.Book;
import com.ssg.proyectorecuperacion.model.BookDetail;
import com.ssg.proyectorecuperacion.network.ApiService;
import com.ssg.proyectorecuperacion.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPagerAdapter extends RecyclerView.Adapter<DetailPagerAdapter.ViewHolder> {

    private List<Book> books;
    private Context context;

    public DetailPagerAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, year, description;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitleDetail);
            year = itemView.findViewById(R.id.txtYearDetail);
            description = itemView.findViewById(R.id.txtDescriptionDetail);
            image = itemView.findViewById(R.id.imgBookDetail);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_detail_page, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Book book = books.get(position);

        Glide.with(context)
                .load(book.getCoverUrl())
                .into(holder.image);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<BookDetail> call =
                apiService.getBookDetail(book.getWorkId());

        call.enqueue(new Callback<BookDetail>() {
            @Override
            public void onResponse(Call<BookDetail> call, Response<BookDetail> response) {

                if (response.isSuccessful() && response.body() != null) {

                    BookDetail detail = response.body();

                    holder.title.setText(detail.getTitle());
                    holder.year.setText(detail.getYear());
                    holder.description.setText(detail.getDescription());
                }
            }

            @Override
            public void onFailure(Call<BookDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}