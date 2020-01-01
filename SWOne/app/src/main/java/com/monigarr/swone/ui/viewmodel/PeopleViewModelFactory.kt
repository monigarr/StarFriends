package com.monigarr.swone.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monigarr.swone.data.PeopleRepository

/*
*   PeopleViewModelFactory creates PeopleViewModel
*
 */

class PeopleViewModelFactory(private val peopleRepository: PeopleRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PeopleViewModel(peopleRepository) as T
    }
}