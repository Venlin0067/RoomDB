package com.example.roomdbexample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Insert
    fun insert(p: Product)

    @Query("SELECT * FROM product_table WHERE name = 'Nitro 5'")
    fun getAll(): List<Product>

    //@Query("SELECT * FROM product_table WHERE name = :name ")
    //fun searchProduct(name:String) : List<Product>
}