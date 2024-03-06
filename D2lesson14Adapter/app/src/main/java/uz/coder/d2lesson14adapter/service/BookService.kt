package uz.coder.d2lesson14adapter.service

import uz.coder.d2lesson14adapter.model.BookModel

interface BookService {
    fun add(bookModel: BookModel)
    fun edit(bookModel: BookModel)
    fun delete(bookModel: BookModel)
    fun listAll():List<BookModel>
    fun getBookCount():Int
    fun getBookId():Int
}