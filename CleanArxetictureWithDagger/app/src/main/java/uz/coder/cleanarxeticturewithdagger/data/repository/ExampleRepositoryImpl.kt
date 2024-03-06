package uz.coder.cleanarxeticturewithdagger.data.repository

import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleLocalDataSuorce
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleLocalDataSuorceImpl
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleRemoteDataSuorce
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleRemoteDataSuorceImpl
import uz.coder.cleanarxeticturewithdagger.data.mapper.ExampleMapper
import uz.coder.cleanarxeticturewithdagger.domain.ExampleRepository

class ExampleRepositoryImpl (
    private val localDataSuorce: ExampleLocalDataSuorceImpl,
    private val remoteDataSuorce: ExampleRemoteDataSuorceImpl,
    private val mapper: ExampleMapper
) :ExampleRepository{
    override fun metod() {
        localDataSuorce.metod()
        remoteDataSuorce.metod()
        mapper.map()
    }
}