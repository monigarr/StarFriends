package com.monigarr.swone.api

import com.monigarr.swone.model.PeopleList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface SwapiService {

    /**
     * Get Star Wars Characters
     */

    @GET
    fun getPeopleList(@Url url : String) : Observable<PeopleList>

}