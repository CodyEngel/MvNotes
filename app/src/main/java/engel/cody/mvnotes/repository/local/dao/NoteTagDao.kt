package engel.cody.mvnotes.repository.local.dao

import androidx.room.Dao
import androidx.room.Query
import engel.cody.mvnotes.repository.local.entity.NoteTagEntity

/**
 * @author cody
 */
@Dao
interface NoteTagDao : MutatorDao<NoteTagEntity> {

    @Query("DELETE FROM notetag WHERE noteId = :noteId")
    fun deleteFromNote(noteId: Int)
}