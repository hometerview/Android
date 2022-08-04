package com.ftw.hometerview.ui.buildinglist

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.ftw.hometerview.databinding.ActivityBuildingListBinding
import kotlinx.parcelize.Parcelize

class BuildingListActivity : AppCompatActivity() {

    companion object {
        val ARGUMENT_KEY = "ARGUMENT_KEY"
        fun newIntent(context: Context, location: String, cnt: Int): Intent {
            return Intent(context, BuildingListActivity::class.java)
                .putExtra(ARGUMENT_KEY, Argument(location, cnt))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityBuildingListBinding = ActivityBuildingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cntTextview.text = intent.getParcelableExtra<Argument>(ARGUMENT_KEY)?.cnt.toString()
        binding.locationTextview.text = "${intent.getParcelableExtra<Argument>(ARGUMENT_KEY)?.location.toString()} 건물"

    }

    @Parcelize
    private data class Argument(
        val location: String,
        val cnt: Int
    ) : Parcelable

}
