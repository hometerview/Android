package com.ftw.hometerview.ui.onboardingnonresult.enrolladdress

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ftw.hometerview.databinding.FragmentEnrollAddressBinding
import com.ftw.hometerview.ui.loading.LoadingActivity
import com.ftw.hometerview.ui.onboardingnonresult.OnboardingNonResultActivity

private const val ARG_PARAM1 = "result"

class EnrollAddressFragment : Fragment() {

    private var result: String? = null
    private lateinit var binding: FragmentEnrollAddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            result = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnrollAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enrollCompanyButton.setOnClickListener {
            startActivity(LoadingActivity.newIntent(requireContext()))
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(result: String) =
            EnrollAddressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, result)
                }
            }
    }
}
