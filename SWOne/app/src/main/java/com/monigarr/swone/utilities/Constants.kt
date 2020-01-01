package com.monigarr.swone.utilities

class Constants {

    private constructor()

    companion object {
        const val SWAPI_PEOPLE_URL: String = "https://swapi.co/api/people/"

        //TODO: integrate schema features
        const val SWAPI_PEOPLE_SCHEMA_URL: String = "https://swapi.co/api/people/schema"

        const val SHARED_PREFERENCE_NAME: String = "swone_shared_preference"

        const val FAVE_PEOPLE_LIST: String = "fave_people_list"
        //TODO: integrate remove fave feature
        const val NOT_FAVE_PEOPLE_LIST: String = "not_fave_people_list"
        const val NEXT_URL_PEOPLE_LIST: String = "next_url_people_list"

        const val EMPTY_VIEW_TYPE = 0
        const val NORMAL_VIEW_TYPE = 1

        const val KEY_PEOPLE_DATA = "people_data"
        const val FAVE_PEOPLE_DATA = "fave_people_data"
        const val NOT_FAVE_PEOPLE_DATA = "not_fave_people_data"
    }

}