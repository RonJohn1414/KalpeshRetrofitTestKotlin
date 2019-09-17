package com.example.kalpeshretrofittestkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kalpeshretrofittestkotlin.R
import com.example.kalpeshretrofittestkotlin.model.DataList
import com.example.kalpeshretrofittestkotlin.util.getProgrssDrawable
import kotlinx.android.synthetic.main.item_list.view.*

class DataListAdapter(var datas: ArrayList<DataList>): RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {


fun updateData(newData: List<DataList>){
    datas.clear()
    datas.addAll(newData)
    notifyDataSetChanged()
}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= DataViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
    )

    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    class DataViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val userId = view.tv_userIdData
        private val entryId = view.tv_EntryIdData
        private val title = view.tv_titleData
        private val body = view.tv_bodyData
        private val progressDrawable = getProgrssDrawable(view.context)


        fun bind(data: DataList){
            userId.text = data.userId
            entryId.text = data.entryId
            title.text = data.title
            body.text = data.body

        }
    }
}

