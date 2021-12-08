package com.example.gonggam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GroupJoinedFragment : Fragment() {

    var joinData = mutableListOf<GroupData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_group_joined, container, false)

        val gAdapter = GroupAdapter(requireContext())
        view.findViewById<RecyclerView>(R.id.rv_groupjoined).adapter = gAdapter

        view.findViewById<RecyclerView>(R.id.rv_groupjoined).layoutManager = LinearLayoutManager(requireContext())

        joinData.apply {
            add(GroupData(name="마사모",lock = true,total="5",member = "1"))
            add(GroupData(name="그룹1", lock = false, total = "4", member = "2"))
        }

        gAdapter.datas = joinData
        gAdapter.notifyDataSetChanged()

        val dividerItemDecoration = DividerItemDecoration(view.findViewById<RecyclerView>(R.id.rv_groupjoined).context, LinearLayoutManager(requireContext()).orientation)
        view.findViewById<RecyclerView>(R.id.rv_groupjoined).addItemDecoration(dividerItemDecoration)

        return view
    }


    fun newInstant() : GroupJoinedFragment
    {
        val args = Bundle()
        val frag = GroupJoinedFragment()
        frag.arguments = args
        return frag
    }
}