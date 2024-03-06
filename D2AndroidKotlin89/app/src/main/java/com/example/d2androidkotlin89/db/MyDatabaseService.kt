package com.example.d2androidkotlin89.db

import com.example.d2androidkotlin89.model.CategoryModel
import com.example.d2androidkotlin89.model.TaomModel

interface MyDatabaseService {

    //category
    fun addCategory(categoryModel: CategoryModel)
    fun deleteCategory(categoryModel: CategoryModel)
    fun editCategory(categoryModel: CategoryModel)
    fun allListCategory():List<CategoryModel>

    //taomlar
    fun addTaom(taomModel: TaomModel)
    fun deleteTaom(taomModel: TaomModel)
    fun editTaom(taomModel: TaomModel)
    fun allListTaom():List<TaomModel>
    fun getTaomById(id:Int):List<TaomModel>

}