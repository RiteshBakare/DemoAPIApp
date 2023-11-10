package com.gmail.at.riteshbakare420

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.gmail.at.riteshbakare420.api.RetrofitInstance
import com.gmail.at.riteshbakare420.databinding.ActivityAddDataBinding
import com.gmail.at.riteshbakare420.models.Sample
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.AddData.setOnClickListener {
            addData()
        }

    }

    private fun addData() {
        val name = binding.etName.text.toString()
        val no = binding.etNo.text.toString()

        val sample = Sample(name = name, no = no)

        RetrofitInstance.api.addData(sample = sample).enqueue(object : Callback<Sample> {
            override fun onResponse(call: Call<Sample>, response: Response<Sample>) {
                Toast.makeText(this@AddDataActivity, "Data Added Successfully", Toast.LENGTH_LONG)
                    .show()
                Log.e("MY_TAG", "Data Added :-) ")
                Log.e("MY_TAG", "body: ${response.body()?.name} and ${response.body()?.no}")
            }

            override fun onFailure(call: Call<Sample>, t: Throwable) {
                Toast.makeText(this@AddDataActivity, "Not Added :-( ", Toast.LENGTH_LONG).show()
                Log.e("MY_TAG", "Not Added :-(")

            }

        })

    }

}