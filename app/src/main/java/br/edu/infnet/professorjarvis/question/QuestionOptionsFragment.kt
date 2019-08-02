package br.edu.infnet.professorjarvis.question


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import br.edu.infnet.professorjarvis.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class QuestionOptionsFragment : Fragment() {

    private lateinit var questionViewModel: QuestionViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.let {
            questionViewModel = ViewModelProviders.of(it)
                .get(QuestionViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_question_options, container, false)
    }


}
