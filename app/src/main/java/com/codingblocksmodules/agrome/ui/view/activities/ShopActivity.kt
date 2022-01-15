package com.codingblocksmodules.agrome.ui.view.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingblocksmodules.agrome.data.api.Client
import com.codingblocksmodules.agrome.data.model.ShopItem
import com.codingblocksmodules.agrome.databinding.ActivityShopBinding
import com.codingblocksmodules.agrome.ui.adapter.ShopAdapter
import com.codingblocksmodules.agrome.util.NoConnectionInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopActivity : AppCompatActivity() {
    val list = arrayListOf<ShopItem>()
    private lateinit var arrayAdapter: ShopAdapter
    private lateinit var binding:ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting up title of shop activity
        supportActionBar?.title = "Shop"

        //setting up the adapter for list of shop items
        arrayAdapter = ShopAdapter(list)
        binding.rvShopItems.layoutManager = LinearLayoutManager(this)
        binding.rvShopItems.adapter = arrayAdapter
        binding.rvShopItems.isNestedScrollingEnabled = false

        //using retrofit library to make a network API call

        GlobalScope.launch(Dispatchers.Main) {
            val progressDialog = ProgressDialog(this@ShopActivity)
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Please Wait")
            progressDialog.show()

            try{
                val response = withContext(Dispatchers.IO){
                    Client.api.getShopDetails()
                }

                if(response.isSuccessful){

                    response.body()?.shop.let {
                        it?.let { it1 -> list.addAll(it1) }
                        arrayAdapter.notifyDataSetChanged()
                        progressDialog.dismiss()

                    }
                }else{

                    Toast.makeText(this@ShopActivity, "There was some error while loading data. Please Try again.", Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()

                }
            }catch (e:NoConnectionInterceptor.NoConnectivityException){

                Toast.makeText(this@ShopActivity, e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch (e:NoConnectionInterceptor.NoInternetException){

                Toast.makeText(this@ShopActivity, e.message, Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()

            }catch(e:Exception){


                Toast.makeText(this@ShopActivity, "Some Error Occurred. Please Try Again", Toast.LENGTH_SHORT).show()
                progressDialog.dismiss()
            }

        }
    }
}