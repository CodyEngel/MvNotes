package engel.cody.mvnotes.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author cody
 */
@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val modifiedAt: Long = System.currentTimeMillis()
)