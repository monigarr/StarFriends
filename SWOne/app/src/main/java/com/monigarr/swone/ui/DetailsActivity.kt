package com.monigarr.swone.ui


import android.os.Bundle
import com.monigarr.swone.R
import com.monigarr.swone.broadcastreceiver.ConnectivityReceiver
import com.monigarr.swone.utilities.Constants
import kotlinx.android.synthetic.main.activity_details.*
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.google.android.material.snackbar.Snackbar
import com.monigarr.swone.`interface`.DetailsPresenter
import com.monigarr.swone.ui.view.DetailsView
import com.monigarr.swone.data.PeopleData


class DetailsActivity : BaseActivity(), DetailsView, ConnectivityReceiver.ConnectivityReceiverListener {
    private var peopleData: PeopleData?= null
    private var characterDetailsPresenter: DetailsPresenter? = null
    private var connectivityReceiver: ConnectivityReceiver?= null
    private var isOnNetworkConnectionChanged: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.title = getString(R.string.character_details)

        if (intent != null) {
            peopleData = intent.getParcelableExtra(Constants.KEY_PEOPLE_DATA) as PeopleData
        }

        connectivityReceiver =
            ConnectivityReceiver(this)
        initViews()
        initPresenter()
        Snackbar.make(tv_details_name, getString(R.string.loading_please_wait), Snackbar.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(connectivityReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected && isOnNetworkConnectionChanged) {

        }
        //Added for unwanted initial call at startup
        isOnNetworkConnectionChanged = true
    }

    private fun initPresenter() {
        characterDetailsPresenter = DetailsPresenterImpl()
        characterDetailsPresenter?.attachView(this)
    }

    private fun initViews() {
        tv_details_name.text = peopleData?.name
        tv_details_birth_year.text = peopleData?.birth_year
        tv_height.text = peopleData?.height
        tv_mass.text = peopleData?.mass
        tv_hair.text = peopleData?.hair_color
        tv_skin.text = peopleData?.skin_color
        tv_eye.text = peopleData?.eye_color
        tv_gender.text = peopleData?.gender
        //TODO: change text as relevant for add or remove from shared prefs
        favebutton.text = "Add to Faves"
    }

    override fun onDestroy() {
        super.onDestroy()
        characterDetailsPresenter?.detachView()
    }

}