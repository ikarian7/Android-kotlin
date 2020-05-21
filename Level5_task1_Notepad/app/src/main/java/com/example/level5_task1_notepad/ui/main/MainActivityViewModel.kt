package com.example.level5_task1_notepad.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.level5_task1_notepad.database.NoteRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()

}
