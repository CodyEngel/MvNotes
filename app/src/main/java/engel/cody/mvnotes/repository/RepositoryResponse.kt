package engel.cody.mvnotes.repository

/**
 * @author cody
 */
sealed class RepositoryResponse {
    data class Success<T>(val response: T) : RepositoryResponse()

    data class Error(val message: String) : RepositoryResponse()
}