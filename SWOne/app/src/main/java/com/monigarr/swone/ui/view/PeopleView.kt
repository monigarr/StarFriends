package com.monigarr.swone.ui.view

import com.monigarr.swone.data.PeopleData


interface PeopleView {
    fun updateRecyclerView(peopleDataList: ArrayList<PeopleData>)
    fun updateRecyclerViewForSearch(peopleDataList: ArrayList<PeopleData>)

    //TODO: integrate fave features: crud
    fun updateRecyclerViewForFaves(peopleDataList: ArrayList<PeopleData>)
}