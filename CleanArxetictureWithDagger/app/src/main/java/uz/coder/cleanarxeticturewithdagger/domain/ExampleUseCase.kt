package uz.coder.cleanarxeticturewithdagger.domain

class ExampleUseCase (val  repository: ExampleRepository){
    operator fun invoke() = repository.metod()
}