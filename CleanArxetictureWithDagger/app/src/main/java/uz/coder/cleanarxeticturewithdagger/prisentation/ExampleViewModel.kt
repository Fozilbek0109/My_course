package uz.coder.cleanarxeticturewithdagger.prisentation

import dagger.internal.DaggerCollections
import uz.coder.cleanarxeticturewithdagger.dagger.DaggerComponentService
import uz.coder.cleanarxeticturewithdagger.domain.ExampleUseCase

class ExampleViewModel(private val useCase:ExampleUseCase) {




    fun metod(){
        useCase()
    }
}