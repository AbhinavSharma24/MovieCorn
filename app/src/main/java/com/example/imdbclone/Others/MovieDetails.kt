package com.example.imdbclone.Others

data class MovieDetails (
    val character:String,
    val name:String,
    val profile_path:String,
    val id: Int
)
data class Cast(val cast:ArrayList<MovieDetails>)

/*ata class TvDetails(
    val character:String,
    val name:String,
    val profile_path:String,
    val id: Int
)
data class TvCast(val cast: ArrayList<TvDetails>)*/

data class Trailers(
    val key:String,
    val name:String
)
data class Trailerarray(val results:ArrayList<Trailers>)

data class Overview(
    val original_title:String,
    val overview:String,
    val first_air_date:String,
    val status:String,
    val release_date:String,
    val runtime:Int,
    val poster_path:String,
    val vote_average:Float,
    val backdrop_path:String,
    val genres:ArrayList<Genres>

)
data class Genres(
    val name: String
)

data class Castinfo(
    val name:String,
    val profile_path:String,
    val biography:String,
    val place_of_birth:String,
    val birthday:String
)
data class Moviecast(
    //val profile_path:String,
    val character:String,
    val poster_path:String,
    val title:String,
    val id:Int
)
data class Moviecastarray(val cast:ArrayList<Moviecast>)

data class Tvcast(
    val character:String,
    val name: String,
    val poster_path:String,
    val title:String,
    val id:Int
)
data class Tvcastarray(val cast:ArrayList<Tvcast>)

data class Searchdetails(
    val title:String,
    val name: String,
    val first_air_date: String,
    val poster_path:String,
    val overview: String,
    val release_date:String,
    val id:Int
)
data class Searcharray(val results:ArrayList<Searchdetails>)

