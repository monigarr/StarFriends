package com.monigarr.swone.`interface`

import com.monigarr.swone.data.PeopleData
import com.monigarr.swone.ui.view.PeopleView

interface PeoplePresenter {
    fun sendPeopleListRequest(url: String)
    fun attachView(peopleView: PeopleView)
    fun detachView()
    fun searchPeopleByName(query: String, peopleDataList: ArrayList<PeopleData>)

    //TODO: integrate fave features: crud
    fun showFavesClick()
    fun addFavesClick()
    fun removeFavesClick()
}