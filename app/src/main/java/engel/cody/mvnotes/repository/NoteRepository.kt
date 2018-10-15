package engel.cody.mvnotes.repository

/**
 * NoteRepository provides functionality to retrieve and save notes.
 * @author cody
 */
interface NoteRepository {

    suspend fun retrieveNotes(): RepositoryResponse

    suspend fun saveNote(note: Note): RepositoryResponse
}