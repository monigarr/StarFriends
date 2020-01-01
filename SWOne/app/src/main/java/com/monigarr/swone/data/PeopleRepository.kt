package com.monigarr.swone.data

/*
*   now we can pass mock version of DAO to the PeopleRepository for testing
*   with dependency injection
*
 */
class PeopleRepository private constructor(private val peopleDao: PeopleDao) {

    fun addPeople(people: PeopleData){
        peopleDao.addPeople(people)
    }

    fun addFavePeople(people: PeopleData){
        peopleDao.addFavePeople(people)
    }

    fun removeFavePeople(people: PeopleData){
        peopleDao.removeFavePeople(people)
    }

    fun getPeople() = peopleDao.getPeople()

    fun getFavePeople() = peopleDao.getFavePeople()

    companion object {
        //singleton
        @Volatile private var instance: PeopleRepository? = null

        fun getInstance(peopleDao: PeopleDao) = instance ?: synchronized(this){
            instance ?: PeopleRepository(peopleDao).also {
                instance = it
            }
        }
    }

}