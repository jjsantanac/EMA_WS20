package com.example.reminderproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentManager


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [user_input.newInstance] factory method to
 * create an instance of this fragment.
 */
class user_input : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null



    fun closeFragment(){
        val transaction = parentFragmentManager.beginTransaction()
        val fragment=user_input()
        transaction.addToBackStack(null)
        transaction.commit()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
        val name=view
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view=view

        if (view != null) {
           val name=view.findViewById<EditText>(R.id.name).text.toString()
            val age=view.findViewById<EditText>(R.id.age).text.toString()
            val height=view.findViewById<EditText>(R.id.height).text.toString()
            val weight=view.findViewById<EditText>(R.id.weight).text.toString()

            val namefield=view.findViewById<TextView>(R.id.namefield)
            val agefield=view.findViewById<TextView>(R.id.agefield)
            val heightfield=view.findViewById<TextView>(R.id.heightfield)
            val weightfield=view.findViewById<TextView>(R.id.weightfield)


            namefield.text=name
            agefield.text=age
            heightfield.text=height
            weightfield.text=weight

            val savechanges=view.findViewById<Button>(R.id.savedata)

            savechanges.setOnClickListener({})
        }




        return inflater.inflate(R.layout.fragment_user_input, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment user_input.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                user_input().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}