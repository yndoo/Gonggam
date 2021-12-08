package com.example.gonggam

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gonggam.databinding.FragmentGroupBinding


class GroupFragment : Fragment() {

    private var _binding : FragmentGroupBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setHasOptionsMenu(true)
    }

    // 다이얼로그 불러내는 코드들 -> 눌리긴 하는데 다이얼로그가 안 뜸
    /*
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(R.id.bar_group).inflateMenu(R.menu.menu_addtab)



        view.findViewById<Toolbar>(R.id.bar_group).setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.item_add ->{
                    // 플러스버튼눌렀을때 실행할 코드
                    val dialog = CreateGroupDialog()
                    val bundle :Bundle = Bundle()
                    bundle.putString("key", "balue")

                    //dialog.show(supportFragmentManager, )
                    GroupFragment().arguments = bundle


                    parentFragmentManager.beginTransaction()
                        .replace(R.id.framelayout_creategroup, GroupFragment())
                        .commit()

                    Toast.makeText(requireContext(), "플러스버튼", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }

        }


    }
*/



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGroupBinding.inflate(inflater, container, false)
        val view = binding.root

        // 버튼 클릭시 대화상자 표시
        binding.barGroup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_add -> {
                    val dialog = CreateGroupDialog()

                    dialog.show(requireActivity().supportFragmentManager, null)


                    Toast.makeText(requireContext(), "플러스버튼", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

            val pagerAdapter = GroupNestedFragmentAdapter(childFragmentManager)
            val pager = binding.viewPagerGroup
            pager.adapter = pagerAdapter
            val tab = binding.tabGrouplist
            tab.setupWithViewPager(pager)


            return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_addtab, menu)
    }

/*
    // 툴바 버튼 클릭
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_add ->{
                // 플러스버튼눌렀을때 실행할 코드
                val dialog = CreateGroupDialog()
                val bundle :Bundle = Bundle()
                bundle.putString("key", "balue")

                //dialog.show(supportFragmentManager, )
                GroupFragment().arguments = bundle


                parentFragmentManager.beginTransaction()
                    .replace(R.id.framelayout_creategroup, GroupFragment())
                    .commit()

                Toast.makeText(requireContext(), "플러스버튼", Toast.LENGTH_SHORT).show()
            }
            else-> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

*/



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
            GroupFragment().apply {

            }
    }
}