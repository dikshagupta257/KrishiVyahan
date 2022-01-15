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
import com.codingblocksmodules.agrome.data.model.TransportItem
import com.codingblocksmodules.agrome.ui.adapter.TransportDetailsAdapter
import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransportationFragment : Fragment() {
    val list = arrayListOf<TransportItem>()
    private lateinit var arrayAdapter: TransportDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_transportation, container, false)
        val rvTransportItem = view.findViewById<RecyclerView>(R.id.rvTransportItems)

        //to set up the adapter for list of transport items
        arrayAdapter = TransportDetailsAdapter(list)
        rvTransportItem.layoutManager = LinearLayoutManager(requireContext())
        rvTransportItem.adapter = arrayAdapter

        //using retrofit library to make a network API call
        GlobalScope.launch(Dispatchers.Main) {
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Please Wait")
            progressDialog.show()
            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getTransportDetails()
                }
                if (response.isSuccessful) {
                    response.body()?.transport.let {
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