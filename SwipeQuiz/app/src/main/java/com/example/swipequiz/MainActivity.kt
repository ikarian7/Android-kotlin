package com.example.swipequiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.quiz_questions.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Quiz>()
    private val QuizAdapter = ReminderAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvQuiz.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuiz.adapter = QuizAdapter
        rvQuiz.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuiz)
        for (i in Quiz.QUIZ_QUESTIONS.indices) {
            questions.add(Quiz(Quiz.QUIZ_QUESTIONS[i], Quiz.QUIZ_ANSWERS[i]))
        }
        QuizAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {

                    if(questions[position].quizAnswer == "False"){
                        questions.removeAt(position)
                    }
                    else {
                        Snackbar.make(tvQuestions,"Wrong answer given", Snackbar.LENGTH_LONG).show()
                    }
                    QuizAdapter.notifyDataSetChanged()
                }
                else if (direction == ItemTouchHelper.RIGHT){
                    if(questions[position].quizAnswer == "True"){
                        questions.removeAt(position)
                    }
                    else{
                        Snackbar.make(tvQuestions,"Wrong answer given", Snackbar.LENGTH_LONG).show()

                    }
                    QuizAdapter.notifyDataSetChanged()
                }
            }

        }
        return(ItemTouchHelper((callback)))
    }


}