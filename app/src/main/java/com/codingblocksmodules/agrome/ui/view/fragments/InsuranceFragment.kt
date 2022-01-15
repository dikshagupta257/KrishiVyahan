package com.codingblocksmodules.agrome.ui.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.api.Client
import com.codingblocksmodules.agrome.data.model.InsuranceItem
import com.codingblocksmodules.agrome.ui.adapter.InsuranceDetailsAdapter
import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InsuranceFragment : Fragment() {
    val list = arrayListOf<InsuranceItem>()
    private lateinit var arrayAdapter: InsuranceDetailsAdapter
    private lateinit var rvInsuranceItem: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_insurance, container, false)
        rvInsuranceItem = view.findViewById(R.id.rvInsuranceItems)

        //setting up the adapter to show list of insurance labs
        arrayAdapter = InsuranceDetailsAdapter(list)
        rvInsuranceItem.layoutManager = LinearLayoutManager(requireContext())
        rvInsuranceItem.adapter = arrayAdapter

        //using retrofit library to make a network API call
        GlobalScope.launch(Dispatchers.Main) {
            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Please Wait")
            progressDialog.show()

            try {
                val response = withContext(Dispatchers.IO) {
                    Client.api.getInsuranceDetails()
                }
                if (response.isSuccessful) {
                    response.body()?.insurance.let {
                        it?.let { it1 -> list.addAll(it1) }
                        arrayAdapter.notifyDataSetChanged()
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

                Toast.makeText(requireContext(), "Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }
        }

        return view
    }
}