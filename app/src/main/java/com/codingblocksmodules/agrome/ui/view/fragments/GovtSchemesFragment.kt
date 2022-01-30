package com.codingblocksmodules.agrome.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.ui.adapter.GovtSchemesCardAdapter
import com.codingblocksmodules.agrome.ui.adapter.NewsAdapter
import com.codingblocksmodules.agrome.ui.adapter.RecentNewsAdapter
import com.codingblocksmodules.agrome.data.model.GovtSchemesItems
import com.codingblocksmodules.agrome.data.model.NewsItem
import com.codingblocksmodules.agrome.data.model.RecentNewsItem

class GovtSchemesFragment : Fragment() {

    private var schemesList = arrayListOf<GovtSchemesItems>()
    private var recentNewsList = arrayListOf<RecentNewsItem>()
    private var newsList = arrayListOf<NewsItem>()
    private lateinit var schemesAdapter: GovtSchemesCardAdapter
    private lateinit var recentNewsAdapter: RecentNewsAdapter
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_govt_schemes, container, false)
        activity?.title = "Government Schemes"
        val rvGovtSchemes = view.findViewById<RecyclerView>(R.id.rvGovtSchemes)
        val rvRecentNews = view.findViewById<RecyclerView>(R.id.rvRecentNews)
        val rvNews = view.findViewById<RecyclerView>(R.id.rvNews)

        //setting up the adapter to show list of government schemes
        schemesAdapter = GovtSchemesCardAdapter(schemesList)
        rvGovtSchemes.layoutManager = GridLayoutManager(requireContext(),2)
        rvGovtSchemes.adapter = schemesAdapter

        //setting up the adapter to show list of recent news
        recentNewsAdapter = RecentNewsAdapter(recentNewsList)
        rvRecentNews.layoutManager = LinearLayoutManager(requireContext())
        rvRecentNews.adapter = recentNewsAdapter

        //setting up the adapter to show list of news
        newsAdapter = NewsAdapter(newsList)
        rvNews.layoutManager = LinearLayoutManager(requireContext())
        rvNews.adapter = newsAdapter

        prepareSchemesItem()
        prepareNewsItem()
        prepareRecentNewsItem()

        return view
    }

    //creating list of news to show on list
    private fun prepareNewsItem() {
        newsList.add(
            NewsItem("GOVERNMENT SCHEMES FOR AGRICULTURE","https://www.youtube.com/embed/YxmIIaXtKeI",
                "In this presentation title  “GOVERNMENT SCHEMES FOR AGRICULTURE” covering various topics relates to agricultural schemes.")
        )

        newsList.add(
            NewsItem("Government added 4 new schemes for farmers in 2021","https://www.youtube.com/embed/41Hc6TOKvWs",
        "5 best Scheme for Farmers which can help you achieve your farming to next level.Here is the top 5 scheme:\n" +
                "\n" +
                "1) Kisan Samman Nidhi yojan\n" +
                "2) Fasal Bima yojana\n" +
                "3) Rashtriya Krishi Bazaar ( National Farmer market)\n" +
                "4) Organic Farming \n" +
                "5) Soil Health card scheme")
        )
        newsAdapter.notifyDataSetChanged()
    }

    //creating list of recent news to show on recent news list
    private fun prepareRecentNewsItem() {
        recentNewsList.add(
            RecentNewsItem(R.drawable.news1,
            "US milk production continues its upward trajectory for 2018"
        ,"21 Dec 2021"
        )
        )

        recentNewsList.add(
            RecentNewsItem(R.drawable.news2,
            "USDA'S crop ratings more ahead for corn, drop for soybeans"
            ,"21 Dec 2021"
        )
        )

        recentNewsList.add(
            RecentNewsItem(R.drawable.news3,
            "Auction report: Bids aplenty for massive John Deere collection"
            ,"21 Dec 2021"
        )
        )

        recentNewsList.add(
            RecentNewsItem(R.drawable.news4,
            "Wool prices expected to remain competitive as demand is grow"
            ,"21 Dec 2021"
        )
        )

        recentNewsAdapter.notifyDataSetChanged()
    }

    //creating list of govt schemes to show on govt schemes list
    private fun prepareSchemesItem() {
        schemesList.add(GovtSchemesItems(R.drawable.scheme1,getString(R.string.govt_scheme1)))
        schemesList.add(GovtSchemesItems(R.drawable.scheme2,getString(R.string.govt_scheme2)))
        schemesList.add(GovtSchemesItems(R.drawable.scheme3,getString(R.string.govt_scheme3)))
        schemesList.add(GovtSchemesItems(R.drawable.scheme4,getString(R.string.govt_scheme4)))
        schemesList.add(GovtSchemesItems(R.drawable.scheme5,getString(R.string.govt_scheme5)))
        schemesList.add(GovtSchemesItems(R.drawable.scheme6,getString(R.string.govt_scheme6)))

        schemesAdapter.notifyDataSetChanged()

    }
}