import entities.Result
import entities.ResultRepository
import entities.ResultUser
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy
import java.io.File

private const val RESULT_JSON = "result.json"

@OptIn(ExperimentalSerializationApi::class)
private val json = Json {
	namingStrategy = JsonNamingStrategy.SnakeCase
}

suspend fun main() {
	println("Getting data from GitHub...")

	val ayfri = GitHubAPI.getUser()
	println("Got user '${ayfri.name}'.")

	val repos = GitHubAPI.getAllUserRepos().toMutableList()
	println("Got ${repos.size} repos.")

	repos.sortWith(compareBy({ it.owner.login }, { it.private }, { it.archived }, { it.name }))

	val result = Result(ResultUser.fromUser(ayfri), repos.map { ResultRepository.fromRepository(it) })
	println("Got result, mapped repositories, got commits/readme/watchers count.")

	val resultJson = json.encodeToString(result)
	val file = File(RESULT_JSON)
	file.writeText(resultJson)

	println("Wrote result to file '$RESULT_JSON'.")
}
