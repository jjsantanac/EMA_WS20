package com.example.reminderproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [handgelenk_uebungen.newInstance] factory method to
 * create an instance of this fragment.
 */
class handgelenk_uebungen : Fragment(R.layout.fragment_handgelenk_uebungen) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    fun playVideo(){
        val uebung_type="string"
        val action=handgelenk_uebungenDirections.actionHandgelenkUebungenToDemonstrateUebung(uebung_type)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uebungbutton=view.findViewById<TextView>(R.id.handgelenk1_textview)
        val uebung2button=view.findViewById<TextView>(R.id.handgelenk2_textview)
        val uebung3button=view.findViewById<TextView>(R.id.handgelenk3_textview)


        uebungbutton.setOnClickListener {playVideo()}
        uebung2button.setOnClickListener {playVideo()}
        uebung3button.setOnClickListener {playVideo()}


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_handgelenk_uebungen, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment handgelenk_uebungen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            handgelenk_uebungen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}