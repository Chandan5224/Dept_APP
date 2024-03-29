package com.example.deptapp.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.deptapp.data.MySingleton
import com.example.deptapp.data.RoutineData
import com.example.deptapp.data.SyllabusData
import com.example.deptapp.databinding.FragmentAcademicsBinding
import com.example.deptapp.util.ConnectionManager


class AcademicsFragment : Fragment() {

    private lateinit var binding: FragmentAcademicsBinding
    val mRoutineArray = ArrayList<RoutineData>()
    val mSyllabusArray = ArrayList<SyllabusData>()
    val TAG = "ACADEMICS FRAGMENT"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAcademicsBinding.inflate(layoutInflater, container, false)
        fetchDataRoutine()
        fetchDataSyllabus()
        return binding.root
    }

    private fun fetchDataRoutine() {
        val url = "https://ill-moth-stole.cyclic.app/api/routine/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                binding.shimmerSyllabus.stopShimmer()
                binding.shimmerRoutines.stopShimmer()
                binding.shimmerSyllabus.visibility = View.GONE
                binding.shimmerRoutines.visibility = View.GONE
                binding.layoutSyllabus.visibility = View.VISIBLE
                binding.layoutRoutine.visibility = View.VISIBLE

                val routineJsonArray = it.getJSONArray("response")
                for (i in 0 until routineJsonArray.length()) {
                    val routineJsonObject = routineJsonArray.getJSONObject(i)
                    val routine = RoutineData(
                        routineJsonObject.getString("batch"),
                        routineJsonObject.getString("pdfurl")
                    )
                    mRoutineArray.add(routine)
                }
                if(mSyllabusArray.isEmpty())
                    fetchDataRoutine()
                else
                    setDataRoutine()

            },
            {
//                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                Log.d(TAG, it.message.toString())
            }
        ) {
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }

    private fun fetchDataSyllabus() {
        val url = "https://ill-moth-stole.cyclic.app/api/syllabus/fetch"
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                binding.shimmerSyllabus.stopShimmer()
                binding.shimmerRoutines.stopShimmer()
                binding.shimmerSyllabus.visibility = View.GONE
                binding.shimmerRoutines.visibility = View.GONE
                binding.layoutSyllabus.visibility = View.VISIBLE
                binding.layoutRoutine.visibility = View.VISIBLE

                val syllabusJsonArray = it.getJSONArray("response")
                for (i in 0 until syllabusJsonArray.length()) {
                    val syllabusJsonObject = syllabusJsonArray.getJSONObject(i)
                    val syllabus = SyllabusData(
                        syllabusJsonObject.getString("batch"),
                        syllabusJsonObject.getString("pdfurl")
                    )
                    mSyllabusArray.add(syllabus)
                }

                if(mSyllabusArray.isEmpty()) {
//                    Toast.makeText(binding.root.context, "Data not found!", Toast.LENGTH_SHORT)
//                        .show()
                    Log.d(TAG, "Data not found")
                }else
                    setDataSyllabus()
            },
            {
//                Toast.makeText(binding.root.context,"Error",Toast.LENGTH_SHORT).show()
                Log.d(TAG, it.message.toString())
            }
        ) {
        }
        MySingleton.getInstance(binding.root.context).addToRequestQueue(jsonObjectRequest)
    }


    private fun setDataRoutine() {
        binding.btnRoutinesForthYear.setOnClickListener {
            val pdfUrl = mRoutineArray[3].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }
        binding.btnRoutinesThirdYear.setOnClickListener {
            val pdfUrl = mRoutineArray[2].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }
        binding.btnRoutinesSecondYear.setOnClickListener {
            val pdfUrl = mRoutineArray[1].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }
        binding.btnRoutinesFirstYear.setOnClickListener {
            val pdfUrl = mRoutineArray[0].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfUrl))
        }
    }

    private fun setDataSyllabus(){
        binding.btnSyllabusFourthYear.setOnClickListener {
            val pdfurl = mSyllabusArray[3].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
        }
        binding.btnSyllabusThirdYear.setOnClickListener {
            val pdfurl = mSyllabusArray[2].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
        }
        binding.btnSyllabusSecondYear.setOnClickListener {
            val pdfurl = mSyllabusArray[1].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
        }
        binding.btnSyllabusFirstYear.setOnClickListener {
            val pdfurl = mSyllabusArray[0].pdfurl
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse(pdfurl))
        }
    }
}