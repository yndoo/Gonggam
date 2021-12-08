package com.example.gonggam

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.gonggam.databinding.DialogCreategroupBinding

class CreateGroupDialog : DialogFragment() {

    private var _binding : DialogCreategroupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val v = super.onCreateView(inflater, container, savedInstanceState)
        _binding = DialogCreategroupBinding.inflate(inflater, container, false)
        val v = binding.root

        val spinner = binding.spCrgrHowto


        // 다이얼로그 모양둥글게했으니 그 뒤 배경을 투명하게 함
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        // 스피너
        spinner.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.itemList, android.R.layout.simple_spinner_item)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0-> {
                        // 선택되면 실행할 부분, 스톱워치 인증
                    }
                    1->{
                        // 선택되면 실행할 부분, 사진 인증
                    }
                    2->{
                        // 선택되면 실행할 부분, AI 인증
                    }
                    3->{
                        // 선택되면 실행할 부분, 기타
                    }
                    else->{
                        //일치하는게 없는 경우우
                   }
                }
            }
        }

        binding.btnCrgrOk.setOnClickListener {
            dismiss() // 대화상자를 닫는 함수
        }

        //var result = arguments?.getString("key")
        // 다이얼로그 내부 것들의 기능

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}