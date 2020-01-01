package com.monigarr.swone.ui

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.monigarr.swone.R
import com.monigarr.swone.`interface`.PeoplePresenter
import com.monigarr.swone.adapter.PeopleAdapter
import com.monigarr.swone.broadcastreceiver.ConnectivityReceiver
import com.monigarr.swone.data.PeopleData
import com.monigarr.swone.ui.view.PeopleView
import com.monigarr.swone.ui.viewmodel.PeopleViewModel
import com.monigarr.swone.utilities.Constants
import com.monigarr.swone.utilities.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_people.*
import kotlinx.android.synthetic.main.list_people.*

//TODO: write tests, run tests for all crud features (fave shared prefs & api peoplelist & network)
class PeopleActivity : BaseActivity(), PeopleView, PeopleAdapter.Listener,
    ConnectivityReceiver.ConnectivityReceiverListener {
    private var peoplePresenter: PeoplePresenter? = null
    private var isLoadingData: Boolean = false
    private var lastVisibleItemPosition: Int = 0
    private var peopleAdapter: PeopleAdapter? = null
    private var connectivityReceiver: ConnectivityReceiver? = null
    private var isOnNetworkConnectionChanged: Boolean = false
    private val PREFS_FAVES = "prefs_faves"


    //called by os when activity first created.
    //initialize ui elements & data objects here.
    //use savedInstanceState to recreate state here
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        supportActionBar?.title = getString(R.string.app_name)

        connectivityReceiver = ConnectivityReceiver(this)
        initSharedPreferences()
        initViews()
        initRecyclerView()
        initPresenter()
        initFaveButtons()
    }

    //called as this activity enters foreground
    //restart or init anything we released during onPause()
    override fun onResume() {
        super.onResume()
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    //called before sliding into background
    //stop visuals & audio here
    override fun onPause() {
        super.onPause()
        unregisterReceiver(connectivityReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected && isLoadingData && isOnNetworkConnectionChanged) {
            Snackbar.make(
                character_list_recycler_view,
                getString(R.string.loading_please_wait),
                Snackbar.LENGTH_SHORT
            )
                .show()
            SharedPreferencesManager.getNetworkCalliingUrl(Constants.NEXT_URL_PEOPLE_LIST)?.let {
                peoplePresenter?.sendPeopleListRequest(
                    it
                )
            }
        }
        //Added for unwanted initial call at startup
        isOnNetworkConnectionChanged = true
    }

    private fun initSharedPreferences() {
        SharedPreferencesManager.init(this)
        SharedPreferencesManager.removeString(Constants.NEXT_URL_PEOPLE_LIST)
    }

    private fun initPresenter() {
        peoplePresenter = PeoplePresenterImpl()
        peoplePresenter?.attachView(this)
        isLoadingData = true
        et_search_box.isEnabled = false
        Snackbar.make(
            character_list_recycler_view,
            getString(R.string.loading_please_wait),
            Snackbar.LENGTH_SHORT
        )
            .show()
        SharedPreferencesManager.getNetworkCalliingUrl(Constants.NEXT_URL_PEOPLE_LIST)?.let {
            peoplePresenter?.sendPeopleListRequest(
                it
            )
        }
    }

    private fun initViews() {
        et_search_box.addTextChangedListener(afterTextChanged = { text: Editable? ->
            run {
                if (text.isNullOrEmpty()) {
                    peopleAdapter?.restoreAdapterToInitialState()
                } else {
                    peopleAdapter?.getActualAdapterDataList()?.let {
                        peoplePresenter?.searchPeopleByName(
                            text.toString().trim(),
                            it
                        )
                    }
                }
            }
        })
    }


    private fun initRecyclerView() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        character_list_recycler_view.layoutManager = layoutManager

        peopleAdapter = PeopleAdapter(this)

        character_list_recycler_view.adapter = peopleAdapter
        character_list_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        character_list_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                lastVisibleItemPosition =
                    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                //Show Star Wars People
                if (et_search_box.text.trim().length == 0 && !isLoadingData &&
                    totalItemCount == lastVisibleItemPosition + 1
                    && SharedPreferencesManager.getNetworkCalliingUrl(Constants.NEXT_URL_PEOPLE_LIST)?.isNotEmpty()!!
                ) {
                    /**
                     * get people from shared preferences
                     */
/*
                    SharedPreferencesManager.getString(Constants.NEXT_URL_PEOPLE_LIST)?.let {
                        peoplePresenter?.sendPeopleListRequest(
                            it
                        )

                    }

 */

                    /**
                     * get favepeople from shared preferences
                     */
                    /* SharedPreferencesManager.getFave(Constants.NEXT_URL_PEOPLE_LIST)?.let {
                         peoplePresenter?.sendPeopleListRequest(
                             it
                         )
                     }
                     */

                    Snackbar.make(
                        character_list_recycler_view,
                        getString(R.string.loading_please_wait),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    isLoadingData = true
                    et_search_box.isEnabled = false
                }
            }
        })
    }

    fun initFaveButtons() {
        val imageButton = findViewById<ImageButton>(R.id.ib_fave)
        imageButton?.setOnClickListener {
            Snackbar.make(
                character_list_recycler_view,
                getString(R.string.loading_please_wait),
                Snackbar.LENGTH_SHORT
            )
                .show()
            ib_fave.setImageResource(R.drawable.ic_action_starsolid)
        }
    }

    //final callback from os before activity is destroyed.
    //stop background threads & long running resources to avoid memory leaks
    override fun onDestroy() {
        super.onDestroy()
        peoplePresenter?.detachView()
    }

    override fun updateRecyclerView(peopleDataList: ArrayList<PeopleData>) {
        (character_list_recycler_view.adapter as PeopleAdapter).updateAdapter(peopleDataList)
        isLoadingData = false
        Snackbar.make(
            character_list_recycler_view,
            getString(R.string.loading_successful),
            Snackbar.LENGTH_SHORT
        )
            .show()
        et_search_box.isEnabled = true
    }

    override fun updateRecyclerViewForSearch(peopleDataList: ArrayList<PeopleData>) {
        (character_list_recycler_view.adapter as PeopleAdapter).updateAdapterForSearch(
            peopleDataList
        )
        character_list_recycler_view.adapter = peopleAdapter
    }

    //TODO: integrate fave features: crud
    override fun updateRecyclerViewForFaves(peopleDataList: ArrayList<PeopleData>){
        //get items from shared prefs
    }

    /*
    fun updateRecyclerViewForFaves(peopleDataList: ArrayList<PeopleData>) {
        (character_list_recycler_view.adapter as PeopleAdapter).updateAdapterForFave(peopleDataList)
        character_list_recycler_view.adapter = peopleAdapter
    }

     */

    //SEND LIST ITEM DETAILS TO DETAILS ACTIVITY WHEN LIST ITEM CLICKED
    override fun onItemClick(peopleData: PeopleData) {
        val peopleDataIntent = Intent(this, DetailsActivity::class.java)
        peopleDataIntent.putExtra(Constants.KEY_PEOPLE_DATA, peopleData)
        startActivity(peopleDataIntent)
    }

    //TODO: integrate fave features: crud
    //CLICK FAVE STAR BUTTON: add item to shared prefs & change button to solid start
    override fun addFavesClick(peopleData: PeopleData) {
        //add this list item to faves shared preferences
        SharedPreferencesManager.putString(Constants.KEY_PEOPLE_DATA, "test").let {
            //change this list item image button to solid star
            ib_fave.setImageResource(R.drawable.ic_action_starsolid)
        }
    }

    //TODO:
    // SHOW FAVE LIST FROM SHARED PREFS - DO NOT SHOW ANYTHING FROM SWAPI URL
    override fun showFavesClick(peopleData: PeopleData){

    }

    //TODO:
    //REMOVE FAVE ITEM FROM SHARED PREFS
    override fun removeFavesClick(peopleData: PeopleData){

    }

    //TODO:
    // ADD OR REMOVE ITEM TO LOCAL SHARED PREFERENCES WHEN LIST ITEM STAR BUTTON CLICKED
    /* fun onFaveClick(peopleData: PeopleData) {
         //light star button if Not in faves

         //dark star button if in faves

         //if clicked & not in faves, add to fave shared prefs
         //switch_fave.setOnClickListener(PeopleViewModel.clickListener)
         ib_fave.setOnClickListener(PeopleViewModel.clickListener);

         //if clicked & is in faves, remove from fave shared prefs

         /*val toggle: Switch = findViewById(R.id.switch_fave)
         toggle.setOnCheckedChangeListener { _, isChecked ->
             if (isChecked) {
                 // The toggle is enabled
             } else {
                 // The toggle is disabled
             }
         }*/
     }*/


}