package uz.coder.cleanarxeticturewithdagger.data.data_source

import uz.coder.cleanarxeticturewithdagger.data.network.ExampleApiService

class ExampleRemoteDataSuorceImpl(private val exampleApiService: ExampleApiService):ExampleRemoteDataSuorce {
    override fun metod() {
        exampleApiService.metod()
    }
}