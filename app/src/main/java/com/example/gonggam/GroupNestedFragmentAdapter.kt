package com.example.gonggam

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GroupNestedFragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    //tab의 개수만큼 return
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        val fragment = when(position){
            0->GroupRecommendFragment().newInstant()
            1->GroupJoinedFragment().newInstant()
            else->GroupRecommendFragment().newInstant()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position){
            0->"추천 그룹"
            1->"나의 그룹"
            else -> "main"
        }

        return title
    }

}