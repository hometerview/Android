package com.ftw.hometerview.ui.searchcompanynonresult.searchcompanyaddress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentSearchCompanyAddressBinding
import com.ftw.hometerview.ui.searchcompanynonresult.enrollcompanyname.EnrollAddressFragment

class SearchCompanyAddressFragment : Fragment() {

    private lateinit var binding: FragmentSearchCompanyAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchCompanyAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 보내기 동작
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.enroll_fragment,
                        EnrollAddressFragment.newInstance(result = binding.searchButton.text.toString())
                    ).commit()
            }
            true
        }
    }

}