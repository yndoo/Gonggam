package com.example.gonggam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GroupRecommendFragment : Fragment() {

    var recommendData = mutableListOf<GroupData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_group_recommend, container, false)

        val gAdapter = GroupAdapter(requireContext())
        v.findViewById<RecyclerView>(R.id.rv_grouprecommend).adapter = gAdapter

        v.findViewById<RecyclerView>(R.id.rv_grouprecommend).layoutManager = LinearLayoutManager(requireContext())

        recommendData.apply {
            add(GroupData(name="그루비룸",lock = true,total="5",member = "4"))
            add(GroupData(name="그룹31", lock = false, total = "4", member = "2"))
            add(GroupData(name="3조", lock = false, total = "4", member = "3"))
            add(GroupData(name="공부하자", lock = false, total = "10", member = "8"))
            add(GroupData(name="마사모",lock = true,total="5",member = "1"))
            add(GroupData(name="숭실대 소프트웨어학부 모여라", lock = false, total = "8", member = "4"))
        }

        gAdapter.datas = recommendData
        gAdapter.notifyDataSetChanged()

        val dividerItemDecoration = DividerItemDecoration(v.findViewById<RecyclerView>(R.id.rv_grouprecommend).context, LinearLayoutManager(requireContext()).orientation)
        v.findViewById<RecyclerView>(R.id.rv_grouprecommend).addItemDecoration(dividerItemDecoration)

        return v
    }

    fun newInstant() : GroupRecommendFragment{
        val args = Bundle()
        val frag = GroupRecommendFragment()
        frag.arguments = args

        return frag
    }


}