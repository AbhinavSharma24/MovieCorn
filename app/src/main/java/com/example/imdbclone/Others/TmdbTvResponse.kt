package com.example.imdbclone.Others

data class TmdbTvResponse(
    val id: Int,
    val name:String,
    //val release_date:Date,
    val vote_average:Float,
    val poster_path:String,
    val backdrop_path:String
)
data class TmdbTv(val results:ArrayList<TmdbTvResponse>)