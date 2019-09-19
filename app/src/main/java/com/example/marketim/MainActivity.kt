package com.example.marketim

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.marketim.Classes.Helper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prefs = this.getSharedPreferences(Helper.PREFS_FILENAME, 0)
        initializeView()

        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            login()
        }
    }

    private fun initializeView() {
        val rememberMe = prefs!!.getBoolean(Helper.PREFS_REMEMBER_ME, false)

        if (rememberMe)
            goOrdersActivity()

        /*val username = prefs!!.getString(Helper.PREFS_USERNAME, null)

        if (!username.isNullOrBlank())
            etUsername.setText(username)*/
    }

    private fun login() {
        resetView()

        if (checkInputs()) {
            if (checkParameters())
                loginSuccess()
            else
                loginFailure()
        }
    }

    private fun resetView() {
        tvLoginError.visibility = View.INVISIBLE
    }

    private fun loginFailure() {
        tvLoginError.visibility = View.VISIBLE
    }

    private fun loginSuccess() {
        tvLoginError.visibility = View.INVISIBLE
        val rememberMe = sRememberMe.isChecked

        if (rememberMe)
            activateAutoLogin()
        else
            deactivateAutoLogin()

        goOrdersActivity()
    }

    private fun goOrdersActivity() {
        val intent = Intent(applicationContext, OrdersActivity::class.java)
        startActivity(intent)
    }

    private fun deactivateAutoLogin() {
        prefs!!.edit().putBoolean(Helper.PREFS_REMEMBER_ME, false).apply()
    }

    private fun activateAutoLogin() {
        prefs!!.edit().putBoolean(Helper.PREFS_REMEMBER_ME, true).apply()
    }

    private fun removeUsernameFromSharedPref() {
        prefs!!.edit().remove(Helper.PREFS_USERNAME).apply()
    }

    private fun saveUsernameToSharedPref() {
        val username = etUsername.text.toString()

        prefs!!.edit().putString(Helper.PREFS_USERNAME, username).apply()
    }

    private fun checkInputs(): Boolean {
        var checker = true
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        if (username.isBlank()) {
            etUsername.error = getString(R.string.e_username_null_blank)
            checker = false
        }

        if (password.isBlank()) {
            etPassword.error = getString(R.string.e_password_null_blank)
            checker = false
        }

        return checker
    }

    private fun checkParameters(): Boolean {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()

        return username == "kariyer" && password == "2019ADev"
    }
}
