package com.ssg.proyectorecuperacion.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssg.proyectorecuperacion.R;
import com.ssg.proyectorecuperacion.model.Book;
import com.ssg.proyectorecuperacion.ui.DetailActivity;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> listBooks;
    private Context context;

    public BookAdapter(List<Book> listBooks, Context context){
        this.listBooks=listBooks;
        this.context=context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, author, year, category, status;
        ImageView image;

        public ViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.txtTitle);
            author=itemView.findViewById(R.id.txtAuthor);
            year=itemView.findViewById(R.id.txtYear);
            category=itemView.findViewById(R.id.txtCategory);
            status=itemView.findViewById(R.id.txtStatus);
            image=itemView.findViewById(R.id.imgBook);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Book book = listBooks.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.year.setText(book.getYear());
        holder.category.setText(book.getCategory());
        holder.status.setText(book.getStatus());

        holder.itemView.setOnClickListener(v ->{
           Intent intent = new Intent(context, DetailActivity.class);
           intent.putExtra("title", book.getTitle());
           intent.putExtra("author", book.getAuthor());
           intent.putExtra("year", book.getAuthor());
           intent.putExtra("category", book.getCategory());
           intent.putExtra("status", book.getStatus());
           context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return listBooks.size();
    }
}
