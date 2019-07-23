package com.example.imdbclone.Others

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("now_playing?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun nowShowing(): Call<Tmdb2>

    @GET("popular?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun popularMovies(): Call<Tmdb2>

    @GET("upcoming?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&region=US&page=1")
    fun upcoming(): Call<Tmdb2>

    @GET("top_rated?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun toprated(): Call<Tmdb2>

    @GET("{Id}/credits?api_key=20ddfcf94f3bf96b48118c43a689756c")
    fun cast(@Path("Id")id:Int): Call<Cast>

    @GET("{Id}/similar?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun similarmovies(@Path("Id")id:Int): Call<Tmdb2>

    @GET("{Id}/videos?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun trailers(@Path("Id")id:Int): Call<Trailerarray>

    @GET("{Id}?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun overview(@Path("Id")id:Int):Call<Overview>

    @GET("person/{Id}?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun castinfo(@Path("Id")id:Int):Call<Castinfo>

    @GET("person/{Id}/movie_credits?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun moviecast(@Path("Id")id:Int):Call<Moviecastarray>

    @GET("person/{Id}/tv_credits?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun tvcast(@Path("Id")id:Int):Call<Tvcastarray>

    @GET("search/movie?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&query&page=1")
    fun search(@Query("query")q:String):Call<Searcharray>

    @GET("search/tv?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&query&page=1")
    fun searchtv(@Query("query")q:String):Call<Searcharray>

    @GET("airing_today?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun airingToday() :Call<TmdbTv>

    @GET("on_the_air?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun onTheAir() :Call<TmdbTv>

    @GET("popular?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun popularTv() :Call<TmdbTv>

    @GET("top_rated?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun topRatedTv(): Call<TmdbTv>

    @GET ("{Id}/credits?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US")
    fun castTv(@Path("Id")id:Int):Call<Cast>

    @GET ("{Id}/similar?api_key=20ddfcf94f3bf96b48118c43a689756c&language=en-US&page=1")
    fun similarTv(@Path("Id")id:Int): Call<TmdbTv>
}
