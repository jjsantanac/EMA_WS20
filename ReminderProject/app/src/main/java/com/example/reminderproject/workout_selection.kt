package com.example.reminderproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [workout_selection.newInstance] factory method to
 * create an instance of this fragment.
 */
class workout_selection : Fragment() {
    // TODO: Rename and change types of parameters

    private val args: workout_selectionArgs by navArgs()
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_selection, container, false)
    }

    fun playVideo(Button_name:String){

        val action=workout_selectionDirections.actionWorkoutSelectionToDemonstrateUebung(Button_name)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val work_selection=args.workoutnumber
        val uebungbutton=view.findViewById<TextView>(R.id.selection1_textview)
        val uebung2button=view.findViewById<TextView>(R.id.selection2_textview)
        val uebung3button=view.findViewById<TextView>(R.id.selection3_textview)
        val uebung4button=view.findViewById<TextView>(R.id.selection4_textview)
        val uebung5button=view.findViewById<TextView>(R.id.selection5_textview)


        when (work_selection){
            "Workout 1" -> {
                uebungbutton.text = "Chest stretch"
                uebung2button.text = "Upper back stretch"
                uebung3button.text="Wrist twist"
                uebung4button.text="Thigh stretch"
                uebung5button.text="Hamstring stretch"
            }
            "Workout 2" -> {
                uebungbutton.text = "Shouldershrug"
                uebung2button.text = "Neck stretch"
                uebung3button.text="Wrist twist"
                uebung4button.text="Thigh/Hip flexor stretch"
                uebung5button.text="Glute stretch"
            }
            "Workout 3" -> {
                uebungbutton.text = "Wall slides"
                uebung2button.text = "Torso twist"
                uebung3button.text="Wrist downward stretch"
                uebung4button.text="Wrist upward stretch"
                uebung5button.text="Hip flexor stretch"
            }
        }

        uebungbutton.setOnClickListener { playVideo(uebungbutton.text.toString()) }
        uebung2button.setOnClickListener { playVideo(uebung2button.text.toString()) }
        uebung3button.setOnClickListener { playVideo(uebung3button.text.toString()) }
        uebung4button.setOnClickListener { playVideo(uebung4button.text.toString()) }
        uebung5button.setOnClickListener { playVideo(uebung5button.text.toString()) }

        
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment workout_selection.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            workout_selection().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}