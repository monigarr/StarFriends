package com.monigarr.swone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monigarr.swone.R
import com.monigarr.swone.data.PeopleData
import com.monigarr.swone.utilities.Constants
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.list_people.view.*

class DetailsAdapter(private val listener: DetailsAdapter.Listener) : RecyclerView.Adapter<DetailsAdapter.BaseViewHolder<*>>() {

    interface Listener {
        fun onItemClick(people : PeopleData)
    }

    private val peopleList: ArrayList<PeopleData> = ArrayList()
    private var peopleListForSearch: ArrayList<PeopleData>? = ArrayList()

    fun updateAdapter(peopleList: ArrayList<PeopleData>) {
        this.peopleList.addAll(peopleList)
        this.peopleListForSearch?.addAll(peopleList)
        notifyDataSetChanged()
    }

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
            itemView.favebutton.setOnClickListener{

                listener.onItemClick(item)

                //TODO: add & remove this item to shared prefs fave
                // if item in shared prefs, remove it
                // show star outline button on peopleactivity list item

                //TODO:
                // if item is not in shared prefs, add it
                // show solid star button on peopleactivity list item
            }
            itemView.tv_name.text = item.name
            itemView.tv_height.text = item.height
            itemView.tv_details_birth_year.text = item.birth_year
            itemView.tv_mass.text = item.mass
            itemView.tv_eye.text = item.eye_color
            itemView.tv_hair.text = item.hair_color
            itemView.tv_skin.text = item.skin_color
            itemView.favebutton.text = item.fave
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