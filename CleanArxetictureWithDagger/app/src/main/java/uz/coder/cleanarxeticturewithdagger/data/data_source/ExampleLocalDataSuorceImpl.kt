package uz.coder.cleanarxeticturewithdagger.data.data_source

import uz.coder.cleanarxeticturewithdagger.data.database.ExampleDatabase

class ExampleLocalDataSuorceImpl(private val  exampleDatabase: ExampleDatabase):ExampleLocalDataSuorce {
    override fun metod() {
        exampleDatabase.metod()
    }
}