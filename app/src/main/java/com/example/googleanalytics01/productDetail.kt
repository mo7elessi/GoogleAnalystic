package com.example.googleanalytics01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.product_detail.*
import java.util.*

class productDetail : AppCompatActivity() {
    lateinit var name: String
    val googleAnalytics = GoogleAnalytics()
    var start: Long = 0
    var end: Long = 0
    var total: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail)

        name = intent.getStringExtra("name").toString()
        val price = intent.getIntExtra("price", 0)
        val details = intent.getStringExtra("details")
        nameProduct.text = "$name"
        priceProduct.text = "$price"
        detailsProduct.text = "$details"

        googleAnalytics.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        googleAnalytics.trackScreen(name)
        // val c = activity!!.intent.getIntExtra("image",-1)
        // root.imageProduct.setImageResource(c)
        addToCart.setOnClickListener {
            googleAnalytics.trackScreen("addToCart " + name)
        }
    }

    override fun onDestroy() {
        end = Calendar.getInstance().timeInMillis
        total = end - start
        googleAnalytics.time(total, "006", name)
        super.onDestroy()
    }
}
