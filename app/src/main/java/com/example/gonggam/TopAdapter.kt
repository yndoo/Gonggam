package com.example.gonggam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TopAdapter(val context: Context, val TopList:ArrayList<Top10>) : BaseAdapter() {
    override fun getCount(): Int {
        return TopList.size
    }

    override fun getItem(position: Int): Any {
        return TopList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_top10,null)

        val rank = view.findViewById<TextView>(R.id.tv_rank)
        val nickname = view.findViewById<TextView>(R.id.tv_nickname)
        val measured = view.findViewById<TextView>(R.id.tv_measured)

        val top = TopList[position]

        rank.text = top.rank
        nickname.text = top.nickname
        measured.text = top.measured

        return view
    }

}