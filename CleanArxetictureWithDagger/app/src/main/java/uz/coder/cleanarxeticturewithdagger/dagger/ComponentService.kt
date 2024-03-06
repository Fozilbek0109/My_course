package uz.coder.cleanarxeticturewithdagger.dagger

import dagger.Component
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleLocalDataSuorce
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleLocalDataSuorceImpl
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleRemoteDataSuorceImpl
import uz.coder.cleanarxeticturewithdagger.data.database.ExampleDatabase
import uz.coder.cleanarxeticturewithdagger.data.mapper.ExampleMapper
import uz.coder.cleanarxeticturewithdagger.data.network.ExampleApiService
import uz.coder.cleanarxeticturewithdagger.data.repository.ExampleRepositoryImpl
import uz.coder.cleanarxeticturewithdagger.domain.ExampleUseCase
import uz.coder.cleanarxeticturewithdagger.prisentation.ExampleViewModel
import uz.coder.cleanarxeticturewithdagger.prisentation.MainActivity

@Component(modules = [ComponentModule::class])
interface ComponentService {
fun getDatabase():ExampleDatabase
fun getExampleLocaleDataSoure():ExampleLocalDataSuorceImpl
fun getApiService():ExampleApiService
fun getExampleRemoteDataSource():ExampleRemoteDataSuorceImpl
fun getExampleRepoImpl():ExampleRepositoryImpl
fun getMapper():ExampleMapper
fun getExampleUseCase():ExampleUseCase
fun getExapleViewModel():ExampleViewModel
fun inject(activity: MainActivity)
}