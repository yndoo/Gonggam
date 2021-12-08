package com.example.gonggam

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class RankingNestedFragmentAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm) {
    //tab의 개수만큼 return
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        val fragment = when(position){
            0->RankingNestedFragment_Time().newInstant()
            1->RankingNestedFragment_Day().newInstant()
            else->RankingNestedFragment_Time().newInstant()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position){
            0->"공부시간"
            1->"공부일수"
            else -> "main"
        }

        return title
    }

}