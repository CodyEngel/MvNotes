package engel.cody.mvnotes.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author cody
 */
@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = ""
)