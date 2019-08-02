package br.edu.infnet.professorjarvis.question


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import br.edu.infnet.professorjarvis.R

/**
 * A simple [Fragment] subclass.
 *
 */
class QuestionTextFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_question_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
