package com.codingblocksmodules.agrome.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codingblocksmodules.agrome.databinding.ActivityReUpyogItemBinding
import com.codingblocksmodules.agrome.ui.adapter.ScreenSliderAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ReUpyogItemActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReUpyogItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReUpyogItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //to set up name of toolbar according to the card clicked
        when(intent.getStringExtra("ItemName")){
            "Vegetable" -> {
                openVegetablesData()
            }
            "Tea" -> {
                openTeaData()
            }
            "Towels" -> {
                openTowelsData()
            }
            "Egg Shells" -> {
                openEggShellsData()
            }
            "Milk Bottle" -> {
                openMilkBottleData()
            }
            "Leaves" -> {
                openLeavesData()
            }
            "Straw" -> {
                openStrawData()
            }
            "Compost" -> {
                openCompostData()
            }
        }

        //setting up the tab layout to show video and article fragment using slider
        binding.viewPager.adapter = ScreenSliderAdapter(this)
        TabLayoutMediator(binding.tabs,
            binding.viewPager
        ) { tab: TabLayout.Tab, pos: Int ->
            when (pos) {
                0 -> tab.text = "ARTICLES"
                1 -> tab.text = "VIDEOS"
            }
        }.attach()

    }

    private fun openVegetablesData() {
        supportActionBar?.title = "Vegetable Peels and Scraps"
    }

    private fun openTeaData() {
        supportActionBar?.title = "Used Tea Leaves and Tea Bags"
    }

    private fun openTowelsData() {
        supportActionBar?.title = "Paper Towels and Napkins"
    }

    private fun openEggShellsData() {
        supportActionBar?.title = "Egg Shells"
    }

    private fun openMilkBottleData() {
        supportActionBar?.title = "Old Water/Milk Bottle/Jugs"
    }

    private fun openLeavesData() {
        supportActionBar?.title = "Dry Leaves"
    }

    private fun openStrawData() {
        supportActionBar?.title = "Straw/Hays"
    }

    private fun openCompostData() {
        supportActionBar?.title = "Compost"
    }
}


