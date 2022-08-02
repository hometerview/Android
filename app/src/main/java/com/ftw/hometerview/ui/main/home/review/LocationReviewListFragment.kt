package com.ftw.hometerview.ui.main.home.review

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ftw.hometerview.R
import com.ftw.hometerview.databinding.FragmentLocationListBinding
import javax.inject.Inject
import kotlinx.parcelize.Parcelize

class LocationReviewListFragment : Fragment() {

    companion object {
        fun newInstance(location: String): LocationReviewListFragment {
            return LocationReviewListFragment().apply {
                arguments = bundleOf(
                    LocationReviewListFragment::class.java.simpleName to Argument(location)
                )
            }
        }
    }

    @Inject
    lateinit var viewModel : LocationListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentLocationListBinding>(
            inflater,
            R.layout.fragment_location_list,
            container,
            false
        )
        return binding.root
    }

    @Parcelize
    private data class Argument(
        val location: String
    ) : Parcelable
}
