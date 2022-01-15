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
import com.codingblocksmodules.agrome.data.model.SellerItem
import com.codingblocksmodules.agrome.ui.adapter.SellerAdapter
import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SellerFragment : Fragment() {
    private val list = arrayListOf<SellerItem>()
    private lateinit var arrayAdapter: SellerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_seller, container, false)
        val rvSellerItems = view.findViewById<RecyclerView>(R.id.rvSellerItems)

        //setting up the adapter to show list of sellers
        arrayAdapter = SellerAdapter(list)
        rvSellerItems.layoutManager = LinearLayoutManager(requireContext())
        rvSellerItems.adapter = arrayAdapter

        //using retrofit library to make a network API call
        GlobalScope.launch(Dispatchers.Main) {
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Please Wait")
            progressDialog.show()

            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getSellerDetails()
                }
                if (response.isSuccessful) {
                    response.body()?.seller.let {
                        it?.let { it1 -> list.addAll(it1) }
                        arrayAdapter.notifyDataSetChanged()
                        progressDialog.dismiss()
                    }
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

                Toast.makeText(requireContext(), "Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }

        return view
    }

}