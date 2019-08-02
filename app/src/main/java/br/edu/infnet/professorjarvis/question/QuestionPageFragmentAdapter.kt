package br.edu.infnet.professorjarvis.question

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class QuestionPageFragmentAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {
    val fragments = listOf(QuestionTextFragment(), QuestionOptionsFragment())

    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}