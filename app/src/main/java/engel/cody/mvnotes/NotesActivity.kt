package engel.cody.mvnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import engel.cody.mvnotes.repository.Note
import engel.cody.mvnotes.repository.NoteRepositoryReal
import engel.cody.mvnotes.repository.RepositoryResponse
import engel.cody.mvnotes.repository.local.NoteDatabase
import kotlinx.android.synthetic.main.activity_notes.*
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.launch

/**
 * @author cody
 */
class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val noteRepository = NoteRepositoryReal(
            NoteDatabase.builder(this)
        )

        GlobalScope.launch {
            val allNotes = noteRepository.retrieveNotes()
            when (allNotes) {
                is RepositoryResponse.Success<*> -> {
                    if (allNotes.response is List<*>) {
                        println("Total Notes: ${allNotes.response.size}")
                    }
                }
            }
        }

        saveNote.setOnClickListener {
            GlobalScope.launch {
                val noteEntity = noteRepository.saveNote(
                    Note(
                        title = noteTitle.text.toString(),
                        body = noteBody.text.toString()
                    )
                )

                println("Note Saved: $noteEntity")
            }
        }
    }
}