package engel.cody.mvnotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notes.*

/**
 * @author cody
 */
class NotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        addNote.setOnClickListener { startActivity(AddNoteActivity.startIntent(this)) }
    }
}