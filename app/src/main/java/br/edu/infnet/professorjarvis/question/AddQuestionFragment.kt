package br.edu.infnet.professorjarvis.question


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import br.edu.infnet.professorjarvis.R
import kotlinx.android.synthetic.main.fragment_add_question.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AddQuestionFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_add_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ATENCAO - sutil: fragmentos dentro de fragmento, usar CHILDfragmentmanager
        question_viewpager.adapter = QuestionPageFragmentAdapter(childFragmentManager)
        subscribe()
        setUpListeners()
    }

    private fun subscribe(){
        questionViewModel.pageNumber.observe(this, Observer {pageNumber->
            question_viewpager.currentItem = pageNumber
            val maxPages = question_viewpager.adapter?.count ?: 0
            continue_button.text = if (pageNumber == maxPages -1){
                "Finalizar"
            } else {
                "Continuar"
            }
        })
    }

    private fun setUpListeners(){
        continue_button.setOnClickListener {
            // número da página atual
            val currentPage = questionViewModel.pageNumber.value ?: 0
            // número máximo de páginas
            val maxPages = question_viewpager.adapter?.count ?: 0
            if (currentPage < maxPages){
                questionViewModel.pageNumber.value = currentPage + 1
            }
        }
    }


}
