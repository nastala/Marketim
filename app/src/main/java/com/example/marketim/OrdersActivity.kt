package com.example.marketim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.marketim.Adapters.ELVOrdersAdapter
import com.example.marketim.Classes.OrdersService
import com.example.marketim.Classes.Product
import com.example.marketim.Classes.ProductDetail
import com.example.marketim.Classes.RetrofitClient
import kotlinx.android.synthetic.main.activity_orders.*
import retrofit2.Call
import retrofit2.Response

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        getOrders()

        /*val product = Product("8", "Ekim", "Market Bro", "Deterjan", 12.0, getString(R.string.os_on_the_way),
            ProductDetail("Deterjan Detay", 12.0))

        val products = arrayListOf(product, product, product)
        val listAdapter = ELVOrdersAdapter(this, products)
        elvOrders.setAdapter(listAdapter)*/
    }

    private fun getOrders() {
        deactivateView()
        val context = this

        RetrofitClient.getClient().create(OrdersService::class.java).getOrders().enqueue(object: retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
                Log.d("OrdersActivity", "getOrders failure error = ${t!!.message}")
                activateView()
            }

            override fun onResponse(
                call: Call<List<Product>>?,
                response: Response<List<Product>>?
            ) {
                val orders = ArrayList(response!!.body())
                Log.d("OrdersActivity", "getOrders success")
                Log.d("OrdersActivity", orders[0].toString())

                val listAdapter = ELVOrdersAdapter(context, orders)
                elvOrders.setAdapter(listAdapter)

                activateView()
            }
        })
    }

    private fun deactivateView() {
        pbOrders.visibility = View.VISIBLE
        elvOrders.isEnabled = false
    }

    private fun activateView() {
        pbOrders.visibility = View.GONE
        elvOrders.isEnabled = true
    }
}
