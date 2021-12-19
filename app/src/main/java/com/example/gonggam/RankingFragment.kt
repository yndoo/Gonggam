package com.example.gonggam

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class RankingFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ranking, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        val user = auth.currentUser
        var username = ""

        val pagerAdapter = RankingNestedFragmentAdapter(childFragmentManager)
        val pager = view.findViewById<ViewPager>(R.id.viewPager_ranking)
        pager.adapter = pagerAdapter
        val tab = view.findViewById<TabLayout>(R.id.tab_timeorday)
        tab.setupWithViewPager(pager)

        database.child("user").child(user!!.uid).child("name").addValueEventListener( object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue()
                username = value.toString()

                view.findViewById<TextView>(R.id.tv_myname_day).setText(username)
                view.findViewById<TextView>(R.id.tv_myname_time).setText(username)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("랭킹탭1","Failed to read user data.")
            }

        })



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RankingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RankingFragment().apply {

            }
    }
}