package com.example.gonggam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


class RankingNestedFragment_Time : Fragment() {

    val Top10List = arrayListOf<Top10>(
        Top10("1.","3조","09:14:00"),
        Top10("2.","시간별","05:42:38"),
        Top10("3.","랭킹","03:01:29"),
        Top10("4.","닉네임","02:58:01"),
        Top10("5.","닉네임","02:12:56"),
        Top10("6.","닉네임","01:18:38"),
        Top10("7.","닉네임","01:09:23"),
        Top10("8.","닉네임","00:58:37"),
        Top10("9.","닉네임","00:30:08"),
        Top10("10.","닉네임","00:17:31"),
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_ranking_nested__time, container, false)

        val Adapter = TopAdapter(requireContext(),Top10List)
        val timeRanking = v.findViewById<ListView>(R.id.list_timeranking)

        timeRanking.adapter = Adapter

        return v
    }


    fun newInstant() : RankingNestedFragment_Time
    {
        val args = Bundle()
        val frag = RankingNestedFragment_Time()
        frag.arguments = args
        return frag
    }

}