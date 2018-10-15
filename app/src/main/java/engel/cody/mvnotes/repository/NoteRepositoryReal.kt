package engel.cody.mvnotes.repository

import engel.cody.mvnotes.repository.local.NoteDatabase
import engel.cody.mvnotes.repository.local.entity.NoteEntity
import engel.cody.mvnotes.repository.local.entity.NoteTagEntity
import engel.cody.mvnotes.repository.local.entity.TagEntity

/**
 * @author cody
 */
class NoteRepositoryReal(private val noteDatabase: NoteDatabase) : NoteRepository {

    /**
     * Int - NoteEntity Hash
     * NoteEntity - Cached NoteEntity
     */
    private val existingNotes = hashMapOf<Int, NoteEntity>()

    /**
     * Int - TagEntity Hash
     * TagEntity - Cached TagEntity
     */
    private val existingTags = hashMapOf<Int, TagEntity>()

    override suspend fun retrieveNotes(): RepositoryResponse {
        val notes: List<Note> = noteDatabase.run {
            val allNotes = getNoteDao().getAllNotes()
            allNotes.map { noteEntity ->
                existingNotes[noteEntity.hashCode()] = noteEntity
                val tagEntities = getTagDao().getTagsForNote(noteEntity.id)
                val tags = tagEntities.map { tagEntity ->
                    existingTags[tagEntity.hashCode()] = tagEntity
                    Tag(name = tagEntity.name, tagHash = tagEntity.hashCode())
                }

                Note(
                    title = noteEntity.title,
                    body = noteEntity.body,
                    modifiedAt = noteEntity.modifiedAt,
                    tags = tags,
                    noteHash = noteEntity.hashCode()
                )
            }
        }

        return RepositoryResponse.Success(notes)
    }

    override suspend fun saveNote(note: Note): RepositoryResponse {
        val savedNoteEntity = writeNoteEntity(note)
        val savedTagEntities = note.tags.map { tag -> writeTagEntity(tag) }
        writeNoteTags(savedNoteEntity, savedTagEntities)

        return RepositoryResponse.Success(noteFromNoteEntity(savedNoteEntity))
    }

    private fun writeNoteTags(noteEntity: NoteEntity, tagEntities: List<TagEntity>) {
        noteDatabase.getNoteTagDao().run {
            deleteFromNote(noteEntity.id)
            tagEntities.forEach { tagEntity ->
                insert(
                    NoteTagEntity(noteEntity.id, tagEntity.id)
                )
            }
        }
    }

    private fun writeTagEntity(tag: Tag): TagEntity {
        val tagEntity: TagEntity = tag.run {
            existingTags[tag.tagHash]?.copy(
                name = name
            ) ?: TagEntity(name = name)
        }

        val savedTagEntity = noteDatabase.getTagDao().run {
            if (tagEntity.id == 0) {
                insert(tagEntity)
            } else {
                update(tagEntity)
            }
            tagEntity
        }

        existingTags.remove(tagEntity.hashCode())
        existingTags[savedTagEntity.hashCode()] = savedTagEntity

        return savedTagEntity
    }

    private fun writeNoteEntity(note: Note): NoteEntity {
        val noteEntity: NoteEntity = note.run {
            existingNotes[note.noteHash]?.copy(
                title = title,
                body = body,
                modifiedAt = System.currentTimeMillis()
            ) ?: NoteEntity(title = title, body = body)
        }

        val savedNoteEntity = noteDatabase.getNoteDao().run {
            if (noteEntity.id == 0) {
                insert(noteEntity)
            } else {
                update(noteEntity)
            }
            noteEntity
        }
        existingNotes.remove(noteEntity.hashCode())
        existingNotes[savedNoteEntity.hashCode()] = savedNoteEntity

        return savedNoteEntity
    }

    private fun noteFromNoteEntity(noteEntity: NoteEntity): Note {
        return noteEntity.run {
            Note(title = title, body = body, modifiedAt = modifiedAt, tags = emptyList(), noteHash = hashCode())
        }
    }
}