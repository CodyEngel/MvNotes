package engel.cody.mvnotes.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import engel.cody.mvnotes.repository.local.dao.NoteDao
import engel.cody.mvnotes.repository.local.dao.NoteTagDao
import engel.cody.mvnotes.repository.local.dao.TagDao
import engel.cody.mvnotes.repository.local.entity.NoteEntity
import engel.cody.mvnotes.repository.local.entity.NoteTagEntity
import engel.cody.mvnotes.repository.local.entity.TagEntity

/**
 * @author cody
 */
@Database(entities = [NoteEntity::class, NoteTagEntity::class, TagEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    abstract fun getNoteTagDao(): NoteTagDao

    abstract fun getTagDao(): TagDao

    companion object {
        fun builder(context: Context): NoteDatabase {
            return Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "notes_database")
                .build()
        }
    }
}