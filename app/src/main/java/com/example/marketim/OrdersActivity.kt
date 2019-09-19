package com.example.marketim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marketim.Adapters.ELVOrdersAdapter
import com.example.marketim.Classes.Product
import com.example.marketim.Classes.ProductDetail
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val product = Product("8", "Ekim", "Market Bro", "Deterjan", 12.0, getString(R.string.os_on_the_way),
            ProductDetail("Deterjan Detay", 12.0))

        val products = arrayListOf(product, product, product)
        val listAdapter = ELVOrdersAdapter(this, products)
        elvOrders.setAdapter(listAdapter)
    }
}
