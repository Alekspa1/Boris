package com.example.boris.APIClass

data class MoviesClass(
    val ageRating: Int,
    val alternativeName: String,
    val backdrop: Backdrop,
    val budget: Budget,
    val countries: List<Country>,
    val description: String,
    val enName: String,
    val externalId: ExternalId,
    val facts: List<Fact>,
    val fees: Fees,
    val genres: List<Genre>,
    val id: Int,
    val imagesInfo: ImagesInfo,
    val logo: Logo,
    val movieLength: Int,
    val name: String,
    val names: List<Name>,
    val persons: List<Person>,
    val poster: Poster,
    val premiere: Premiere,
    val productionCompanies: List<ProductionCompany>,
    val rating: Rating,
    val ratingMpaa: String,
    val releaseYears: List<ReleaseYear>,
    val reviewInfo: ReviewInfo,
    val seasonsInfo: List<SeasonsInfo>,
    val sequelsAndPrequels: List<SequelsAndPrequel>,
    val shortDescription: String,
    val similarMovies: List<SimilarMovy>,
    val slogan: String,
    val status: String,
    val top10: Int,
    val top250: Int,
    val type: String,
    val typeNumber: Int,
    val videos: Videos,
    val votes: Votes,
    val watchability: Watchability,
    val year: Int
)