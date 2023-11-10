package com.gmail.at.riteshbakare420

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.at.riteshbakare420.adapter.MyAdapter
import com.gmail.at.riteshbakare420.api.ApiInterface
import com.gmail.at.riteshbakare420.api.RetrofitInstance
import com.gmail.at.riteshbakare420.databinding.ActivityMainBinding
import com.gmail.at.riteshbakare420.models.Data
import com.gmail.at.riteshbakare420.models.DemoModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.AddData.setOnClickListener {
            startActivity(Intent(this, AddDataActivity::class.java))

        }

        val a = getAllDataFromAPI()

        if(a == null) {
            Log.e("MY_TAG","error :-( ")
        }
        else {
            val list = a.data
            for (i in list) {
                Log.e("MY_TAG","name: ${i.name}")
                Log.e("MY_TAG","no: ${i.no} \n\n")
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)



    }


    private fun getAllDataFromAPI() : DemoModels? {
        var demoModels : DemoModels? = null
        RetrofitInstance.api.getAllData().enqueue(object : Callback<DemoModels> {
            override fun onResponse(call: Call<DemoModels>, response: Response<DemoModels>) {
                if (response.isSuccessful) {
                    Log.e("MY_TAG", "Response is Successful ")
                    Toast.makeText(this@MainActivity, "Successfully", Toast.LENGTH_SHORT).show()
                    Log.e("MY_TAG", "data: ${response.body()}")

                    demoModels = response.body()!!

                    for (i in demoModels!!.data) {
                        Log.e("MY_TAG","name: ${i.name}")
                        Log.e("MY_TAG","no: ${i.no} \n\n")
                    }
                    binding.recyclerView.adapter = MyAdapter(demoModels!!.data)
                }
                else {
                    Log.e("MY_TAG", "Response is Failure")
                    Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DemoModels>, t: Throwable) {
                Log.e("MY_TAG", "error: ${t.message}")
                Toast.makeText(this@MainActivity, "Error ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
        return demoModels
    }


    private fun getData() {
        RetrofitInstance.api.getText().enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.e("MY_TAG", "Response is Successful ")
                    Toast.makeText(this@MainActivity, "Successfully", Toast.LENGTH_SHORT).show()
                    Log.e("MY_TAG", "data: ${response.body()}")
                } else {
                    Log.e("MY_TAG", "Response is Failure")
                    Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("MY_TAG", "error: ${t.message}")
                Toast.makeText(this@MainActivity, "Error ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }

    fun someTask() {
        RetrofitInstance.api.getText().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}