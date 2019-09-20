package com.example.marketim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.marketim.Adapters.ELVOrdersAdapter
import com.example.marketim.Classes.*
import kotlinx.android.synthetic.main.activity_orders.*
import retrofit2.Call
import retrofit2.Response

class OrdersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        getOrders()

        btnLogout.setOnClickListener {
            logout()
        }

        btnOrders.setOnClickListener {
            getOrders()
        }
    }

    private fun logout() {
        val prefs = this.getSharedPreferences(Helper.PREFS_FILENAME, 0)
        prefs!!.edit().putBoolean(Helper.PREFS_REMEMBER_ME, false).apply()
        goLoginActivity()
    }

    private fun goLoginActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        finish()
        startActivity(intent)
    }

    private fun getOrders() {
        deactivateView()
        val context = this

        RetrofitClient.getClient().create(OrdersService::class.java).getOrders().enqueue(object: retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>?, t: Throwable?) {
                Toast.makeText(context, t!!.message, Toast.LENGTH_SHORT).show()
                activateView()
            }

            override fun onResponse(
                call: Call<List<Product>>?,
                response: Response<List<Product>>?
            ) {
                val orders = ArrayList(response!!.body())
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
