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
 * Use the [browse_workouts.newInstance] factory method to
 * create an instance of this fragment.
 */
class browse_workouts : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse_workouts, container, false)
    }

    private fun selectWorkout(workout_num:String){

        val action=browse_workoutsDirections.actionBrowseWorkoutsToWorkoutSelection(workout_num)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val workoutbutton=view.findViewById<TextView>(R.id.workout1_textview)
        val workout2button=view.findViewById<TextView>(R.id.workout2_textview)
        val workout3button=view.findViewById<TextView>(R.id.workout3_textview)

        workoutbutton.setOnClickListener{selectWorkout(workoutbutton.text.toString())}
        workout2button.setOnClickListener{selectWorkout(workout2button.text.toString())}
        workout3button.setOnClickListener{selectWorkout(workout3button.text.toString())}





    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment browse_workouts.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            browse_workouts().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}