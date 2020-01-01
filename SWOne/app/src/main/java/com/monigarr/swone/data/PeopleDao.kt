package com.monigarr.swone.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/*
* save People data to a MutableList
*
 */
class PeopleDao {

    //database table
    private val peopleList = mutableListOf<PeopleData>()
    //mutalbeLiveData from Architecture Components Library
    //LiveData is observed for changes
    private val people = MutableLiveData<List<PeopleData>>()

    init {
        //connect empty peopleList to
        //mutableLiveData to be observed
        people.value = peopleList
    }

    fun addPeople(person: PeopleData){
        peopleList.add(person)
        //after adding people to 'database'
        //update value of MutableLiveData
        //to notify its active observers
        people.value = peopleList
    }

    //Casting MutableLiveData to LiveData so its
    //value should not be changed from other classes
    fun getPeople() = people as LiveData<List<PeopleData>>

    fun getFavePeople() = people as LiveData<List<PeopleData>>

    fun addFavePeople(person: PeopleData){
        peopleList.add(person)
        people.value = peopleList
    }

    fun removeFavePeople(person: PeopleData){
        peopleList.remove(person)
        people.value = peopleList
    }
}