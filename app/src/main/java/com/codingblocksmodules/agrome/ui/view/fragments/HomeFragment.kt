package com.codingblocksmodules.agrome.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.ui.adapter.FarmingPracticesAdapter
import com.codingblocksmodules.agrome.ui.adapter.GovtSchemesCardAdapter
import com.codingblocksmodules.agrome.data.model.FarmingPracticeItem
import com.codingblocksmodules.agrome.data.model.GovtSchemesItems
import com.codingblocksmodules.agrome.databinding.FragmentHomeBinding
import com.synnapps.carouselview.ImageListener

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var itemList = arrayListOf<GovtSchemesItems>()
    private var practicesItemList = arrayListOf<FarmingPracticeItem>()
    private lateinit var govtSchemeAdapter:GovtSchemesCardAdapter
    private lateinit var farmingPracticesAdapter: FarmingPracticesAdapter
    private var carouselImages = intArrayOf(R.drawable.home_bg, R.drawable.home_bg_2, R.drawable.home_bg_3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        //setting up the carousel view
        binding.carouselView.pageCount = carouselImages.size
        binding.carouselView.setImageListener(imageListener)

        //setting the adapter on recycler view of government schemes
        govtSchemeAdapter = GovtSchemesCardAdapter(itemList)
        binding.rvGovtSchemes.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvGovtSchemes.itemAnimator = DefaultItemAnimator()
        binding.rvGovtSchemes.adapter = govtSchemeAdapter

        //setting the adapter on recycler view of government schemes
        farmingPracticesAdapter = FarmingPracticesAdapter(practicesItemList)
        binding.rvFarmingPractices.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFarmingPractices.itemAnimator = DefaultItemAnimator()
        binding.rvFarmingPractices.setHasFixedSize(true)
        binding.rvFarmingPractices.adapter = farmingPracticesAdapter

        prepareSchemesItem()
        preparePracticesItem()

        //setting on click listeners of all the buttons
        binding.btnMoreSchemes.setOnClickListener {
            val govtSchemesFragment = GovtSchemesFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, govtSchemesFragment)?.addToBackStack(null)?.commit()
        }

        binding.btnAboutUs1.setOnClickListener {
            val aboutUsFragment = AboutUsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, aboutUsFragment)?.addToBackStack(null)?.commit()
        }

        binding.btnReadMoreAbout.setOnClickListener {
            val aboutUsFragment = AboutUsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, aboutUsFragment)?.addToBackStack(null)?.commit()
        }

        return view
    }

    //setting up the carousel on home page
    private var imageListener = ImageListener { position, imageView -> imageView?.setImageResource(carouselImages[position]) }

    //creating list of farming practices to show on farming practices list
    private fun preparePracticesItem() {
        practicesItemList.clear()
        practicesItemList.add(
            FarmingPracticeItem(R.drawable.practice1,
            "Conservation Tillage",
            "The conservation tillage aims at addressing wind and water erosion by covering the earth with vegetation (either crops or their residues) and limiting the number of tilling operations.")
        )

        practicesItemList.add(
            FarmingPracticeItem(R.drawable.practice2,
            "Contour Farming",
            "The soil conservation method proves efficient in slope territories and suggests planting species along the contour. Rows up and down the slope provoke soil erosion due to water currents while rows along the contour restrain it.")
        )

        practicesItemList.add(
            FarmingPracticeItem(R.drawable.practice3,
            "Strip Cropping",
            "In this case, farmers combine high-growing crops with low-growing ones for the sake of wind protection, like when corn grows in strips with forage crops.")
        )

        practicesItemList.add(
            FarmingPracticeItem(R.drawable.practice4,
            "Crop Rotation",
            "Crop rotation vs. monocropping farming suggests changing agro species instead of planting one and the same for many subsequent seasons. Farmers applying this soil conservation method reap numerous benefits.")
        )

        practicesItemList.add(
            FarmingPracticeItem(R.drawable.practice5,
            "Buffer Strips",
            "These are trees and bushes on the banks of water bodies to prevent sediment, water wash offs. Their roots fix the soil to avoid slumping and erosion, canopies protect from excessive sunlight to water inhabitants and falling leaves are a source of organic matter and food of minor aquatic animals.")
        )

        practicesItemList.add(
            FarmingPracticeItem(R.drawable.practice6,
            "Grassed Waterways",
            "A grassed waterway is just what it is called. This is a furrow for water streams covered with grass. It is connected to a ditch, pit, or current to collect water, and the grassroots keep the earth in place, protecting it from water erosion, and thus contributing to soil conservation.")
        )

        farmingPracticesAdapter.notifyDataSetChanged()

    }

    //creating list of govt schemes to show on govt schemes list
    private fun prepareSchemesItem() {
        itemList.clear()
        itemList.add(GovtSchemesItems(R.drawable.scheme1,getString(R.string.govt_scheme1)))
        itemList.add(GovtSchemesItems(R.drawable.scheme2,getString(R.string.govt_scheme2)))
        itemList.add(GovtSchemesItems(R.drawable.scheme3,getString(R.string.govt_scheme3)))
        itemList.add(GovtSchemesItems(R.drawable.scheme4,getString(R.string.govt_scheme4)))

        govtSchemeAdapter.notifyDataSetChanged()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}