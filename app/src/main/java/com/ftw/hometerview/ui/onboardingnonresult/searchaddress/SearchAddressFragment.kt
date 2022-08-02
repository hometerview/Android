package com.ftw.hometerview.ui.onboardingnonresult.searchaddress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentSearchAddressBinding
import com.ftw.hometerview.ui.onboardingnonresult.enrolladdress.EnrollAddressFragment

class SearchAddressFragment : Fragment() {

    private lateinit var binding: FragmentSearchAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                // 보내기 동작
                parentFragmentManager.beginTransaction()
                    .replace(R.id.enroll_fragment,
                        EnrollAddressFragment.newInstance(result = binding.searchButton.text.toString())
                    ).commit()
            }
            true
        }
    }

}