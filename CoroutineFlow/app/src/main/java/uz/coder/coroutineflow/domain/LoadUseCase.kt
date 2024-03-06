package uz.coder.coroutineflow.domain

class LoadUseCase(private val repo: Repo) {
    operator fun invoke() = repo.load()
}