package com.monigarr.swone.ui.viewmodel

import android.content.Intent
import android.text.Editable
import android.view.View
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.monigarr.swone.R
import com.monigarr.swone.adapter.PeopleAdapter
import com.monigarr.swone.data.PeopleData
import com.monigarr.swone.data.PeopleRepository
import com.monigarr.swone.ui.DetailsActivity
import com.monigarr.swone.ui.PeoplePresenterImpl
import com.monigarr.swone.utilities.Constants
import com.monigarr.swone.utilities.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_people.*
import kotlinx.android.synthetic.main.list_people.*
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.monigarr.swone.`interface`.PeoplePresenter
import com.monigarr.swone.broadcastreceiver.ConnectivityReceiver
import com.monigarr.swone.ui.PeopleActivity
import com.monigarr.swone.ui.view.PeopleView
import com.monigarr.swone.ui.viewmodel.PeopleViewModel
import kotlinx.android.synthetic.main.activity_people.*
import kotlinx.android.synthetic.main.list_people.*


/*
*   PeopleViewModel: all logic, data, manipulation with data
*   View only calls functions on PeopleViewModel
*       so data wont reset with orientation changes
 */
class PeopleViewModel(private val peopleRepository: PeopleRepository): ViewModel(){

    fun getPeople() = peopleRepository.getPeople()
    fun addPeople(people: PeopleData) = peopleRepository.addPeople(people)

    //TODO: integrate fave features: crud
    //fun getFavePeople() peopleRepository.getFavePeople()
    //fun addFavePeople(people: PeopleData) = peopleRepository.addFavePeople(people)
    //fun removeFavePeople(people: PeopleData) = peopleRepository.removeFavePeople(people)


}