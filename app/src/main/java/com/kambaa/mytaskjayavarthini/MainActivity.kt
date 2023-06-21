package com.kambaa.mytaskjayavarthini

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kambaa.mytaskjayavarthini.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://fakestoreapi.com/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var productAdapter: ProductAdapter
    lateinit var linearLayoutManager:LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var productRecyclerView: RecyclerView=findViewById(R.id.productRecyclerView)


        productRecyclerView.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(this)
        productRecyclerView.layoutManager=linearLayoutManager
        getProducts()
    }

    private fun getProducts() {
        var productRecyclerView: RecyclerView=findViewById(R.id.productRecyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(TaskApiInterface::class.java)

        val retrofitData=retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<ProductDataItem>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<List<ProductDataItem>?>,
                response: Response<List<ProductDataItem>?>,
            ) {
                val responseBody=response.body()!!

                productAdapter= ProductAdapter(baseContext,responseBody)
                productAdapter.notifyDataSetChanged()
                productRecyclerView.adapter=productAdapter

                Log.i("Product Data","OnSuccess")

            }

            override fun onFailure(call: Call<List<ProductDataItem>?>, t: Throwable) {
                Log.d("ProductData","onFailure:"+t.message)
            }
        })
    }
}