package com.codingblocksmodules.agrome.ui.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.api.Client
import com.codingblocksmodules.agrome.data.model.CropRecommendationResponse
import com.codingblocksmodules.agrome.databinding.FragmentCropRecommendationBinding
import com.codingblocksmodules.agrome.ui.adapter.RecommendedCropsAdapter
import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CropRecommendationFragment : Fragment() {
    private lateinit var progressDialog: ProgressDialog
    private lateinit var arrayAdapter: RecommendedCropsAdapter
    private var list = ArrayList<String>()
    private lateinit var nitrogen: String
    private lateinit var phosphorus: String
    private lateinit var potassium: String
    private lateinit var temperature: String
    private lateinit var humidity: String
    private lateinit var ph: String
    private lateinit var rainfall: String
    private lateinit var scrollView: ScrollView
    private var _binding: FragmentCropRecommendationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCropRecommendationBinding.inflate(inflater, container, false)
        val view = binding.root

        scrollView = view.findViewById(R.id.scrollView)

        //setting up the adapter of list of recommended crops
        arrayAdapter = RecommendedCropsAdapter(list)
        binding.rvRecommendedCrops.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecommendedCrops.adapter = arrayAdapter

        binding.btnRecommend.setOnClickListener {
            nitrogen = binding.etNitrogen.text.toString()
            phosphorus = binding.etPhosphorus.text.toString()
            potassium = binding.etPotassium.text.toString()
            temperature = binding.etTemperature.text.toString()
            humidity = binding.etHumidity.text.toString()
            ph = binding.etPH.text.toString()
            rainfall = binding.etRainfall.text.toString()

            //checking for edge cases for all the values entered
            when {
                nitrogen.isEmpty() -> {
                    binding.etNitrogen.error = "Please enter value for Nitrogen."
                    binding.etNitrogen.requestFocus()
                }
                phosphorus.isEmpty() -> {
                    binding.etPhosphorus.error = "Please enter value for Phosphorus."
                    binding.etPhosphorus.requestFocus()
                }
                potassium.isEmpty() -> {
                    binding.etPotassium.error = "Please enter value for Potassium."
                    binding.etPotassium.requestFocus()
                }
                temperature.isEmpty() -> {
                    binding.etTemperature.error = "Please enter value for Temperature."
                    binding.etTemperature.requestFocus()
                }
                humidity.isEmpty() -> {
                    binding.etHumidity.error = "Please enter value for Humidity."
                    binding.etHumidity.requestFocus()
                }
                ph.isEmpty() -> {
                    binding.etPH.error = "Please enter value for PH."
                    binding.etPH.requestFocus()
                }
                rainfall.isEmpty() -> {
                    binding.etRainfall.error = "Please enter value for Rainfall."
                    binding.etRainfall.requestFocus()
                }
                else -> {
                    // to make call to api to get list of recommended crops
                    getRecommendedCropsList()
                }
            }
        }

        return view
    }

    // to make call to api to get list of recommended crops
    private fun getRecommendedCropsList() {

        binding.btnRecommend.isEnabled = false
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please wait.")
        progressDialog.setCancelable(false)
        progressDialog.show()

        temperature = String.format("%.2f", temperature.toFloat())
        humidity = String.format("%.2f", humidity.toFloat())
        ph = String.format("%.2f", ph.toFloat())
        rainfall = String.format("%.2f", rainfall.toFloat())

        //making the api call
        try {
            Client.api.getCropRecommendation(
                nitrogen.toFloat().toInt(),
                phosphorus.toFloat().toInt(),
                potassium.toFloat().toInt(),
                temperature.toFloat(),
                humidity.toFloat(),
                ph.toFloat(),
                rainfall.toFloat(),
            ).enqueue(object : Callback<CropRecommendationResponse> {
                override fun onResponse(
                    call: Call<CropRecommendationResponse>,
                    response: Response<CropRecommendationResponse>
                ) {
                    progressDialog.dismiss()
                    binding.btnRecommend.isEnabled = true
                    list.clear()
                    response.body()?.predict.let {
                        it?.let { it1 -> list.addAll(it1) }
                    }
                    Toast.makeText(
                        requireContext(),
                        "Results Uploaded. Please scroll down to see the results!.",
                        Toast.LENGTH_SHORT
                    ).show()
                    arrayAdapter.notifyDataSetChanged()
                   scrollView.viewTreeObserver.addOnGlobalLayoutListener {
                       scrollView.post {
                           scrollView.fullScroll(View.FOCUS_DOWN)

                       }
                   }
                    binding.tvTextAboveRecommendedCrops.isVisible = true
                }

                override fun onFailure(call: Call<CropRecommendationResponse>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(requireContext(), "Some error occurred", Toast.LENGTH_LONG)
                        .show()
                    binding.btnRecommend.isEnabled = true
                    progressDialog.dismiss()
                    binding.tvTextAboveRecommendedCrops.isVisible = false
                }

            })
        }catch (e: NoConnectionInterceptor.NoConnectivityException){

            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            binding.tvTextAboveRecommendedCrops.isVisible = false
            binding.btnRecommend.isEnabled = true
            progressDialog.dismiss()

        }catch (e: NoConnectionInterceptor.NoInternetException){

            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            binding.tvTextAboveRecommendedCrops.isVisible = false
            binding.btnRecommend.isEnabled = true
            progressDialog.dismiss()

        }catch(e:Exception){

            Toast.makeText(requireContext(), "Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
            binding.tvTextAboveRecommendedCrops.isVisible = false
            binding.btnRecommend.isEnabled = true
            progressDialog.dismiss()

        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}