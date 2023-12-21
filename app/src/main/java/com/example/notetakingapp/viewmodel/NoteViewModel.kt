package com.example.notetakingapp.viewmodel

import android.app.Application
import com.example.notetakingapp.model.Note
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(
    app: Application ,
    private val repository: NoteRepository):
    AndroidViewModel(app)
{

        fun addNote(note:Note)=
            viewModelScope.launch {
            repository.insternote(note)
        }

    fun deleteNote(note:Note)=
        viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun updateNote(note:Note)=
        viewModelScope.launch {
        repository.updateNote(note)
    }

    fun getNotes()= repository.getAllNote()

    fun search(query: String?)=
        repository.seracchNote(query)
}