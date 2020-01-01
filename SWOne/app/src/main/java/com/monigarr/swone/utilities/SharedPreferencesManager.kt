package com.monigarr.swone.utilities

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.monigarr.swone.R
import kotlinx.android.synthetic.main.activity_details.view.*

class SharedPreferencesManager {

    private constructor()

    companion object {
        private var sharedPreferens: SharedPreferences? = null

        /**
         * Initialize with local Shared Preference file found on device
         */
        fun init(context: Context) {
            sharedPreferens = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        }

        /**
         * Add entire list of Star Wars Characters to Shared Preferences
         */
        @SuppressLint("ApplySharedPref")
        fun putString(key: String, value: String) {
            sharedPreferens?.edit()?.putString(key, value)?.apply()
        }

        /**
         * Return Star Wars Characters from Shared Preferences
         */
        fun getString(key: String) : String? {
            return sharedPreferens?.getString(key, Constants.SWAPI_PEOPLE_URL)
        }

        /**
         * Add Fave Star Wars People into Shared Preferences
         */
        fun putFave(key: String, value: String){
            //sharedPreferens?.edit()?.putString(key, value)?.commit()
            //TODO: test & verify threads are playing nice
            sharedPreferens?.edit()?.putString(key, value)?.apply()
        }

        /**
         * Return Fave Star Wars People from Shared Preferences
         */
        fun getFave(key: String) : String? {
            return sharedPreferens?.getString(key, Constants.FAVE_PEOPLE_LIST)
        }

        /**
         * return constant string for networking.
         */
        fun getNetworkCalliingUrl(key: String) : String? {
            return sharedPreferens?.getString(key, Constants.SWAPI_PEOPLE_URL)
        }

        fun removeString(key: String) {
            //immediately
            //sharedPreferens?.edit()?.remove(key)?.commit()

            //background thread
            sharedPreferens?.edit()?.remove(key)?.apply()
        }
    }



}