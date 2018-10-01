package engel.cody.mvnotes.repository.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * @author cody
 */
//@Entity(indices = {@Index("name"),
//    @Index(value = {"last_name", "address"})})
@Entity(
    tableName = "notetag",
    primaryKeys = ["noteId", "tagId"],
    indices = [Index(value = ["noteId"]), Index(value = ["tagId"])],
    foreignKeys = [ForeignKey(
        entity = NoteEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("noteId"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = TagEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("tagId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class NoteTagEntity(
    val noteId: Int,
    val tagId: Int
)