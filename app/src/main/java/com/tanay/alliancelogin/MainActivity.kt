package com.tanay.alliancelogin

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginNow()
    }
    private fun loginNow() {
        val call = RetrofitHelper.getAllService()
            .login(getString(R.string.user_name), getString(R.string.password), "Login")
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Login Failed. Please try again.", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Login Success.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this@MainActivity, "Login Failed. Please try again.", Toast.LENGTH_SHORT)
                        .show()
                }
                finish()
            }

        })
    }
}

