package com.example.imdbclone.Others

import java.util.*

data class TmdbResponse(

    val id: Int,
    val title:String,
    val release_date:Date,
    val vote_average:Float,
    val poster_path:String,
    val backdrop_path:String

)
data class Tmdb2(val results:ArrayList<TmdbResponse>)