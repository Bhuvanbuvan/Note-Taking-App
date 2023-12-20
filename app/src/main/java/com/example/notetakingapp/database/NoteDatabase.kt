package com.example.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetakingapp.model.Note
import java.util.function.LongConsumer

@Database(entities = [Note::class], version = 1 )
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao() :NoteDAO

    //singleton patter -> returning one instence during the runtime from the database.
    companion object{
        @Volatile //made visible to other threads.
        // so any change to this instence would be immediately made visible to other threads.
        private var INSTENCE:NoteDatabase?=null
        private var instance:NoteDatabase?=null
        private val LOCK=Any()

        operator fun invoke(context: Context)=instance?:
        synchronized(LOCK){
            instance?:
            createDatabase(context).also{
                instance=it
            }
        }

        private fun createDatabase(context: Context)=Room.databaseBuilder(context.applicationContext,
            NoteDatabase::class.java,"note_db").build()
        }


        /*   private fun getDatabase(context: Context):NoteDatabase{
               synchronized(this){
                   var instence= INSTENCE
                   if (instence== null){
                       instence= Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"note_db").build()
                   }
                   return instence
               }
           }*/

    }