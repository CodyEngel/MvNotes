package engel.cody.mvnotes.repository.local.dao

import androidx.room.Dao
import androidx.room.Query
import engel.cody.mvnotes.repository.local.entity.NoteEntity

/**
 * @author cody
 */
@Dao
interface NoteDao : MutatorDao<NoteEntity> {

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<NoteEntity>
}