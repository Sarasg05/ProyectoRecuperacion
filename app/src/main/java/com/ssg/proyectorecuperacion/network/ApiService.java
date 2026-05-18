package com.ssg.proyectorecuperacion.network;

import com.ssg.proyectorecuperacion.model.BookDetail;
import com.ssg.proyectorecuperacion.model.BookResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface ApiService {

    @GET("search.json")
    Call<BookResponse> searchBooks(@Query("q") String query);
    @GET("works/{id}.json")
    Call<BookDetail> getBookDetail(@Path("id") String id);

    @GET("search.json")
    Call<BookResponse> getBooksByAuthor(@Query("author") String author);
}
