package com.monigarr.swone.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monigarr.swone.R
import com.monigarr.swone.data.PeopleData
import com.monigarr.swone.utilities.Constants
import com.monigarr.swone.utilities.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_people.view.*
import kotlinx.android.synthetic.main.list_people.view.*

class PeopleAdapter(private val listener: Listener) : RecyclerView.Adapter<PeopleAdapter.BaseViewHolder<*>>() {

    interface Listener {
        //click item in list to view details
        fun onItemClick(people : PeopleData)

        //click switch to show list of items in shared prefs
        fun showFavesClick(people: PeopleData)

        //click star button on each item to add each item to shared prefs
        fun addFavesClick(people : PeopleData)

        //TODO: integrate fave features: crud
        fun removeFavesClick(people: PeopleData)

    }

    private val peopleList: ArrayList<PeopleData> = ArrayList()
    private var peopleListForSearch: ArrayList<PeopleData>? = ArrayList()
    //private var peopleListForFave: ArrayList<PeopleData> = ArrayList()

    fun updateAdapter(peopleList: ArrayList<PeopleData>) {
        this.peopleList.addAll(peopleList)
        this.peopleListForSearch?.addAll(peopleList)
        //this.peopleListForFave?.addAll(peopleList)
        notifyDataSetChanged()
    }

    //TODO: integrate with mvvm
    /*fun updateFaveAdapter(peopleList: ArrayList<PeopleData>) {
        this.peopleList.addAll(peopleList)
        this.peopleListForFave.addAll(peopleList)
        notifyDataSetChanged()
    }*/


    fun restoreAdapterToInitialState() {
        this.peopleListForSearch?.clear()
        this.peopleListForSearch?.addAll(this.peopleList)
        notifyDataSetChanged()
    }

    fun getActualAdapterDataList(): ArrayList<PeopleData> {
        return this.peopleList
    }

    fun updateAdapterForSearch(peopleDataList: ArrayList<PeopleData>) {
        this.peopleListForSearch?.clear()
        this.peopleListForSearch?.addAll(peopleDataList)
    }

    //TODO: integrate with mvvm
    /*
    fun updateAdapterForFave(peopleDataList: ArrayList<PeopleData>) {
        this.peopleListForFave?.clear()
        this.peopleListForFave?.addAll(peopleDataList)
    }
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            Constants.NORMAL_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_people, parent, false)
                NormalViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.list_empty, parent, false)
                EmptyViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (peopleListForSearch?.size == 0) {
            1
        } else {
            peopleListForSearch?.size!!
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder.itemViewType) {
            Constants.NORMAL_VIEW_TYPE -> {
                if (peopleListForSearch?.size!! > 0) {
                    peopleListForSearch?.get(position)?.let { (holder as NormalViewHolder).bind(it) }
                }
            }
            else -> {
                (holder as EmptyViewHolder).bind()
            }
        }

        //TODO: integrate with mvvm
        // else Button Click Load Faves / shared prefs
        //TODO: integrate with mvvm
        // else Button Click Add to Faves

    }

    override fun getItemViewType(position: Int): Int {
        return if (peopleListForSearch?.size == 0) {
            Constants.EMPTY_VIEW_TYPE
        } else {
            Constants.NORMAL_VIEW_TYPE
        }
    }

    inner class NormalViewHolder(view: View) : BaseViewHolder<PeopleData>(view) {
        override fun bind() {
            return
        }

        override fun bind(item: PeopleData) {

            itemView.tv_name.text = item.name

            //VIEW LIST ITEM DETAILS
            itemView.setOnClickListener{ listener.onItemClick(item) }

            //TODO:
            // ADD LIST ITEM TO SHARED PREFS
            itemView.ib_fave.setOnClickListener{ listener.addFavesClick(item) }

            //TODO:
            // SHOW ITEMS IN SHARED PREFS (FAVES
            // itemView.switch_fave.setOnCheckedChangeListener { compoundButton, b ->  }

            /*
           itemView.switch_fave.setOnCheckedChangeListener ( itemView.switch_fave, isChecked ->
               if(itemView.switch_fave.isChecked){
                   //add item to to faves shared prefs
                   itemView.switch_fave.text = "Fave"
               } else {
                   //remove item from faves shared prefs
                   itemView.switch_fave.text = ""
               }
           )

            */

            //TODO:
            // REMOVE LIST ITEM FROM SHARED PREFS
        }

    }

    inner class EmptyViewHolder(view: View) : BaseViewHolder<PeopleData>(view) {
        override fun bind() {
            return
        }

        override fun bind(item: PeopleData) {
            return
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
        abstract fun bind()
    }

}