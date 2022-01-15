package com.codingblocksmodules.agrome.ui.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingblocksmodules.agrome.R
import com.codingblocksmodules.agrome.data.api.Client
import com.codingblocksmodules.agrome.data.model.NutrientsItem
import com.codingblocksmodules.agrome.databinding.FragmentCropAnalysisBinding
import com.codingblocksmodules.agrome.ui.adapter.NutrientsAdapter
import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class CropAnalysisFragment : Fragment() {
    private var _binding: FragmentCropAnalysisBinding? = null
    private val binding get() = _binding!!

    private lateinit var progressDialog: ProgressDialog
    private lateinit var arrayAdapter: NutrientsAdapter
    private var list = ArrayList<NutrientsItem>()
    private lateinit var scrollView: ScrollView
    private lateinit var cropName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCropAnalysisBinding.inflate(inflater, container, false)
        val view = binding.root


        scrollView = view.findViewById(R.id.scrollView)

        //setting up array adapter for showing list of nutrients
        arrayAdapter = NutrientsAdapter(list)
        binding.rvNutrients.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.rvNutrients.adapter = arrayAdapter

        binding.btnSearchNutrients.setOnClickListener {
            cropName = binding.etCropName.text.toString()

            //checking for edge cases for the value entered
            if(cropName.isEmpty()){
                binding.etCropName.error = "Crop name cannot be empty"
                binding.etCropName.requestFocus()
            }else{
                // to make call to api to get list of nutrients of soil
                getNutrientsList()
            }
        }
        return view
    }

    private fun getNutrientsList() {

            binding.btnSearchNutrients.isEnabled = false
            progressDialog = ProgressDialog(requireContext())
            progressDialog.setMessage("Please wait.")
            progressDialog.setCancelable(false)
            progressDialog.show()

        //making the api call
            try {
               Client.api.getNutrients(cropName).enqueue(object : Callback<List<NutrientsItem>> {
                   override fun onResponse(
                       call: Call<List<NutrientsItem>>,
                       response: Response<List<NutrientsItem>>
                   ) {
                       progressDialog.dismiss()
                       binding.btnSearchNutrients.isEnabled = true
                       list.clear()
                       Log.d("response", "onResponse: ${response.body().toString()}")
                       response.body()?.let {
                           list.addAll(it)
                       }
                       Toast.makeText(
                           requireContext(),
                           "Results Uploaded!.",
                           Toast.LENGTH_SHORT
                       ).show()
                       arrayAdapter.notifyDataSetChanged()
                       scrollView.viewTreeObserver.addOnGlobalLayoutListener {
                           scrollView.post {
                               scrollView.fullScroll(View.FOCUS_DOWN)

                           }
                       }

                       binding.tvTextAnalysis.isVisible = true
                       binding.llFourthSection.isVisible = true
                       binding.llThirdSection.isVisible = false
                   }

                   override fun onFailure(call: Call<List<NutrientsItem>>, t: Throwable) {
                       Log.d("response", "onFailure: response failed ${t.message}")
                       Toast.makeText(requireContext(), "Please enter valid crop name!", Toast.LENGTH_LONG).show()
                       progressDialog.dismiss()
                       binding.btnSearchNutrients.isEnabled = true
                       binding.tvTextAnalysis.isVisible = false
                       binding.llFourthSection.isVisible = false
                       binding.llThirdSection.isVisible = true
                   }

               })

            }catch (e: NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                binding.btnSearchNutrients.isEnabled = true
                binding.tvTextAnalysis.isVisible = false
                binding.llFourthSection.isVisible = false
                binding.llThirdSection.isVisible = true
                progressDialog.dismiss()

            }catch (e: NoConnectionInterceptor.NoInternetException){

                Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                binding.btnSearchNutrients.isEnabled = true
                binding.tvTextAnalysis.isVisible = false
                binding.llFourthSection.isVisible = false
                binding.llThirdSection.isVisible = true
                progressDialog.dismiss()

            }catch(e:Exception){

                Toast.makeText(requireContext(), "Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                binding.btnSearchNutrients.isEnabled = true
                binding.tvTextAnalysis.isVisible = false
                binding.llFourthSection.isVisible = false
                binding.llThirdSection.isVisible = true
                progressDialog.dismiss()

            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}