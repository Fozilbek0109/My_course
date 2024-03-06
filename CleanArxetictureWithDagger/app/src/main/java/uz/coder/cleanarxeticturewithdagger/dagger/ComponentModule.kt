package uz.coder.cleanarxeticturewithdagger.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleLocalDataSuorceImpl
import uz.coder.cleanarxeticturewithdagger.data.data_source.ExampleRemoteDataSuorceImpl
import uz.coder.cleanarxeticturewithdagger.data.database.ExampleDatabase
import uz.coder.cleanarxeticturewithdagger.data.mapper.ExampleMapper
import uz.coder.cleanarxeticturewithdagger.data.network.ExampleApiService
import uz.coder.cleanarxeticturewithdagger.data.repository.ExampleRepositoryImpl
import uz.coder.cleanarxeticturewithdagger.domain.ExampleUseCase
import uz.coder.cleanarxeticturewithdagger.prisentation.ExampleViewModel

@Module
class ComponentModule {
    @Provides
    fun provideosExampleDataBase(): ExampleDatabase {
        return ExampleDatabase()
    }

    @Provides
    fun provideosExampleLocalDataSourceImpl(database: ExampleDatabase): ExampleLocalDataSuorceImpl {
        return ExampleLocalDataSuorceImpl(database)
    }

    // api servise
    @Provides
    fun provideosExampleApiService(): ExampleApiService {
        return ExampleApiService()
    }

    @Provides
    fun provideosExampleRemoteDataSorce(apiService: ExampleApiService): ExampleRemoteDataSuorceImpl {
        return ExampleRemoteDataSuorceImpl(apiService)
    }


    // repo imple and mapper
    @Provides
    fun provideosEmapleMapper():ExampleMapper{
        return ExampleMapper()
    }

    @Provides
    fun provideosExampleRepositoryImpl(
        localDataSuorceImpl: ExampleLocalDataSuorceImpl,
        remoteDataSuorceImpl: ExampleRemoteDataSuorceImpl,
        mapper: ExampleMapper
    ): ExampleRepositoryImpl {
        return ExampleRepositoryImpl(localDataSuorceImpl, remoteDataSuorceImpl, mapper)
    }
// use case

    @Provides
    fun provideosExampleUseCase(repositoryImpl: ExampleRepositoryImpl):ExampleUseCase{
        return ExampleUseCase(repositoryImpl)
    }

    // view model

    @Provides
    fun provideosExampleViewModel(useCase: ExampleUseCase):ExampleViewModel{
        return ExampleViewModel(useCase)
    }
}