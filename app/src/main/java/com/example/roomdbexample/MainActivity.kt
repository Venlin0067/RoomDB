package com.example.roomdbexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.roomdbexample.data.Product
import com.example.roomdbexample.data.ProductDB
import com.example.roomdbexample.data.ProductDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var dao: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = ProductDB.getInstance(application).productDao

        val btnInsert : Button = findViewById(R.id.btnInsert)

        btnInsert.setOnClickListener()
        {
            val name: String = findViewById<TextView>(R.id.tfName).text.toString()
            val price: Double = findViewById<TextView>(R.id.tfPrice).text.toString().toDouble()

            val p = Product (0, name, price)

            CoroutineScope(IO).launch {
                dao.insert(p)
            }
        }

        val btnGet : Button = findViewById(R.id.btnGet)
        btnGet.setOnClickListener()
        {
            CoroutineScope(IO).launch {
                var str = ""
                //val pList : List<Product> = dao.searchProduct("Nitro 5")
                val pList : List <Product> = dao.getAll()
                for (p:Product in pList){
                    str += p.name + "\t" + "\t" + "RM" + p.price + "\n"
                }

                CoroutineScope(Main).launch {
                val tv : TextView = findViewById(R.id.tvResult)
                tv.text = str
                }
            }
        }
    }
}