package engel.cody.mvnotes.repository.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

/**
 * @author cody
 */
interface MutatorDao<T> {

    @Delete
    fun delete(entity: T): Int

    @Insert
    fun insert(entity: T): Long

    @Update
    fun update(entity: T): Int
}