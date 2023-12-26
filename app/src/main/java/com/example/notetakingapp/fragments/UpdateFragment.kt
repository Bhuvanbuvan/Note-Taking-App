package com.example.notetakingapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notetakingapp.MainActivity
import com.example.notetakingapp.R
import com.example.notetakingapp.adapter.NoteAdapter
import com.example.notetakingapp.databinding.FragmentNewBinding
import com.example.notetakingapp.databinding.FragmentUpdateBinding
import com.example.notetakingapp.model.Note
import com.example.notetakingapp.viewmodel.NoteViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private var _binding:FragmentUpdateBinding?=null
    private val binding  get()=_binding!!
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var currentnote:Note
    private val args:UpdateFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding=FragmentUpdateBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        noteViewModel=(context as MainActivity).noteViewModel
        currentnote=args.note!!

        binding.etNoteTitleUpdate.setText(currentnote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentnote.noteBody)


        binding.fabDone.setOnClickListener{
            val title=binding.etNoteTitleUpdate.text.toString().trim()
            val body=binding.etNoteBodyUpdate.text.toString().trim()

            if (title.isNotEmpty()){
                val note=Note(currentnote.id,title,body)
                noteViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
            }
            else{
                Toast.makeText(context,"Please Enter The Note Title",Toast.LENGTH_LONG).show()
            }


        }
    }

    private fun delete(){
        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("You Want to Delete this Note")
            setPositiveButton("Delete"){_,_->
                noteViewModel.deleteNote(currentnote)

                view?.findNavController()?.navigate(R.id.action_updateFragment_to_homeFragment)
            }
            setNegativeButton("Cancel",null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete->{
                delete()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}