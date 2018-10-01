package engel.cody.mvnotes.repository.local.dao

import androidx.room.Dao
import androidx.room.Query
import engel.cody.mvnotes.repository.local.entity.TagEntity

/**
 * @author cody
 */
@Dao
interface TagDao : MutatorDao<TagEntity> {

    @Query("SELECT tag.* FROM tag INNER JOIN notetag ON tagId = id WHERE noteId = :noteId")
    fun getTagsForNote(noteId: Int): List<TagEntity>
}