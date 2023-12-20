package com.example.notetakingapp.repository

import androidx.room.Query
import com.example.notetakingapp.database.NoteDatabase
import com.example.notetakingapp.model.Note

class NoteRepository(private val db:NoteDatabase) {
    suspend fun insternote(note: Note)= db.getNoteDao().insertNote(note)

    suspend fun updateNote(note: Note)=db.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note)=db.getNoteDao().deleteNote(note)

    fun getAllNote() =db.getNoteDao().getAllNotes()
    fun seracchNote(query: String?)=db.getNoteDao().searchNotes(query)

}