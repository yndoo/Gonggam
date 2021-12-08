package com.example.gonggam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


class RankingNestedFragment_Day : Fragment() {

    val Top10List = arrayListOf<Top10>(
        Top10("1.","3조","31"),
        Top10("2.","일수별","30"),
        Top10("3.","랭킹","28"),
        Top10("4.","닉네임","21"),
        Top10("5.","닉네임","17"),
        Top10("6.","닉네임","14"),
        Top10("7.","닉네임","9"),
        Top10("8.","닉네임","7"),
        Top10("9.","닉네임","3"),
        Top10("10.","닉네임","1"),
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_ranking_nested__day, container, false)

        val Adapter = TopAdapter(requireContext(),Top10List)
        val dayRanking = v.findViewById<ListView>(R.id.list_dayranking)

        dayRanking.adapter = Adapter

        return v
    }


    fun newInstant() : RankingNestedFragment_Day
    {
        val args = Bundle()
        val frag = RankingNestedFragment_Day()
        frag.arguments = args
        return frag
    }

}