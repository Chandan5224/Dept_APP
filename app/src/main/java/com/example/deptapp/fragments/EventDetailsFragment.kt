package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.deptapp.R
import com.example.deptapp.data.EventData
import com.example.deptapp.databinding.FragmentEventDetailsBinding
import com.google.android.material.navigation.NavigationView

class EventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailsBinding
    private var onBackPressedToken: String? = "HOME"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEventDetailsBinding.inflate(layoutInflater, container, false)

        val desc = arguments?.getString("desc")
        val descSize = desc!!.length
        val desc1 = desc.subSequence(0, descSize/3)
        val desc2 = desc.subSequence(descSize/3, 2*descSize/3)
        val desc3 = desc.subSequence(2*descSize/3, descSize)

        onBackPressedToken = arguments?.getString("onBackPressedToken")


        binding.tvEventDetailsFragTitle.text = arguments?.getString("title")
        if(arguments?.getString("img2") != null) {
            binding.ivImg2EventDetails.visibility = View.VISIBLE
        }
        if(arguments?.getString("img3") != null) {
            binding.ivImg3EventDetails.visibility = View.VISIBLE
        }
        Glide.with(binding.root.context)
            .load(arguments?.getString("img1"))
            .into(binding.ivImg1EventDetails)
        Glide.with(binding.root.context)
            .load(arguments?.getString("img2"))
            .into(binding.ivImg2EventDetails)
        Glide.with(binding.root.context)
            .load(arguments?.getString("img3"))
            .into(binding.ivImg3EventDetails)
        binding.tvDescription1EventDetails.text = desc1
        binding.tvDescription2EventDetails.text = desc2
        binding.tvDescription3EventDetails.text = desc3

        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        onBackPressed()
    }

    private fun onBackPressed() {
        if(onBackPressedToken == "SOCIETY") {
            val fragment = SocietyFragment()
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.frame, fragment)
            fragmentTransaction?.addToBackStack(null)
            activity?.findViewById<NavigationView>(R.id.navigationView)?.setCheckedItem(R.id.society)
            fragmentTransaction?.commit()
        }
    }
}