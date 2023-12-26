package com.example.notetakingapp.repository

import com.example.notetakingapp.database.NoteDatabase
import com.example.notetakingapp.model.Note

class NoteRepository(private val db:NoteDatabase) {
    suspend fun insternote(note:Note)= db.getNoteDao().insertion(note)

    suspend fun updateNote(note: Note)=db.getNoteDao().updateNote(note)

    suspend fun deleteNote(note: Note)=db.getNoteDao().deleteNote(note)

    fun getAllNote() =db.getNoteDao().getAllNotes()
    fun seracchNote(query: String?)=db.getNoteDao().searchNote(query)

}