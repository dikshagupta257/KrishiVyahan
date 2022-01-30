package com.codingblocksmodules.agrome.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codingblocksmodules.agrome.databinding.FragmentReUpyogBinding
import com.codingblocksmodules.agrome.ui.view.activities.ReUpyogItemActivity

class ReUpyogFragment : Fragment() {
    private var _binding: FragmentReUpyogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentReUpyogBinding.inflate(inflater, container, false)
        val view = binding.root

        //handling clicks to send name of item which is clicked using intent
        val intent = Intent(requireContext(), ReUpyogItemActivity::class.java)
        binding.cvVegetable.setOnClickListener {
            intent.putExtra("ItemName", "Vegetable")
            startActivity(intent)
        }
        binding.cvTeaBags.setOnClickListener {
            intent.putExtra("ItemName", "Tea")
            startActivity(intent)
        }
        binding.cvPaperTowels.setOnClickListener {
            intent.putExtra("ItemName", "Towels")
            startActivity(intent)
        }
        binding.cvEggShells.setOnClickListener {
            intent.putExtra("ItemName", "Egg Shells")
            startActivity(intent)
        }
        binding.cvMilkBottle.setOnClickListener {
            intent.putExtra("ItemName", "Milk Bottle")
            startActivity(intent)
        }
        binding.cvDryLeaves.setOnClickListener {
            intent.putExtra("ItemName", "Leaves")
            startActivity(intent)
        }
        binding.cvStrawHays.setOnClickListener {
            intent.putExtra("ItemName", "Straw")
            startActivity(intent)
        }
        binding.cvCompost.setOnClickListener {
            intent.putExtra("ItemName", "Compost")
            startActivity(intent)
        }

        return view
    }

}