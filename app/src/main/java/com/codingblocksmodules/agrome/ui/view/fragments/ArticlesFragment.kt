package com.codingblocksmodules.agrome.ui.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.api.Client
import com.codingblocksmodules.agrome.data.model.Article
import com.codingblocksmodules.agrome.ui.adapter.ArticleAdapter
import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArticlesFragment : Fragment() {
    private val articlesList = arrayListOf<Article>()
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_articles, container, false)
        val rvArticles = view.findViewById<RecyclerView>(R.id.recyclerView)

        //setting up the adapter to show list of videos
        articleAdapter = ArticleAdapter(articlesList)
        rvArticles.layoutManager = LinearLayoutManager(requireContext())
        rvArticles.adapter = articleAdapter

        //to show data for list of articles of item from API depending on which card was clicked
        when(activity?.intent?.getStringExtra("ItemName")){
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

        progressDialog = ProgressDialog(requireContext())
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Please Wait")
        progressDialog.show()
        return view
    }

    private fun openVegetablesData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("1")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

    private fun openTeaData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("2")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

    private fun openTowelsData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("3")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

    private fun openEggShellsData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("4")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

    private fun openMilkBottleData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("5")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

    private fun openLeavesData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("6")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

    private fun openStrawData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("7")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

    private fun openCompostData() {
        GlobalScope.launch(Dispatchers.Main){
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getReUpyogData("8")
                }
                if (response.isSuccessful) {
                    response.body()?.ReUpyog?.Article.let{
                        it?.let { it1 -> articlesList.addAll(it1) }
                        articleAdapter.notifyDataSetChanged()
                    }
                    progressDialog.dismiss()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "There was some error while loading data. Please Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.dismiss()
                }
            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "${e.message} Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }
    }

}