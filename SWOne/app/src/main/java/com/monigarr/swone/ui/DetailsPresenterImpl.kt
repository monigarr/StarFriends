package com.monigarr.swone.ui

import com.monigarr.swone.`interface`.DetailsPresenter
import com.monigarr.swone.ui.view.DetailsView

//TODO: convert to MVVM,  remove mvp architecture
class DetailsPresenterImpl : DetailsPresenter {
    private var detailsView: DetailsView? = null


    override fun attachView(detailsView: DetailsView) {
        this.detailsView = detailsView
    }

    override fun detachView() {
        this.detailsView = null
    }

}