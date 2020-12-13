package com.example.reminderproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [bein_uebungen.newInstance] factory method to
 * create an instance of this fragment.
 */
class bein_uebungen : Fragment(R.layout.fragment_bein_uebungen) {
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

    fun playVideo(Button_name:String){
        val uebung_type=Button_name
        val action=bein_uebungenDirections.actionBeinUebungenToDemonstrateUebung(uebung_type)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uebungbutton=view.findViewById<TextView>(R.id.beine1_textview)
        val uebung2button=view.findViewById<TextView>(R.id.beine2_textview)
        val uebung3button=view.findViewById<TextView>(R.id.beine3_textview)
        val uebung4button=view.findViewById<TextView>(R.id.beine4_textview)
        val uebung5button=view.findViewById<TextView>(R.id.beine5_textview)


        uebungbutton.setOnClickListener {playVideo(uebungbutton.text.toString())}
        uebung2button.setOnClickListener {playVideo(uebung2button.text.toString())}
        uebung3button.setOnClickListener {playVideo(uebung3button.text.toString())}
        uebung4button.setOnClickListener {playVideo(uebung4button.text.toString())}
        uebung5button.setOnClickListener {playVideo(uebung5button.text.toString())}


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bein_uebungen, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            bein_uebungen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}