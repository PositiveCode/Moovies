package com.geneus.moovies.data.repo

import com.geneus.moovies.data.api.ApiHelper
import com.geneus.moovies.data.api.model.GenreResponse
import com.geneus.moovies.data.db.dao.GenreDao
import com.geneus.moovies.data.db.model.Genre
import com.geneus.moovies.utils.Resource

class MovieRepo(
    private val apiHelper: ApiHelper,
    private val genreDao: GenreDao
) {
    suspend fun getMovieGenre(): Result<GenreResponse> {
        return apiHelper.getMovieGenre().onSuccess {
            if(it.genreList.isNotEmpty()){
                for (genre in it.genreList) {
                    val genreModel = Genre(
                        id = genre.id.toInt(),
                        name = genre.name
                    )
                    genreDao.insert(genreModel)
                }
            }
            Resource.success(genreDao.getAllGenre())
        }.onFailure {
            Resource.error(it.message ?: "Network connectivity issue detected.", null)
        }
    }
}