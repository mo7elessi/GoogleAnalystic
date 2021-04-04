package com.example.googleanalytics01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.layout_products.*
import java.util.*
import kotlin.collections.ArrayList


class electronic : Fragment(), productAdapter.onClick {
    private val list = ArrayList<product>()
    val googleAnalytics = GoogleAnalytics()
    private var db: FirebaseFirestore? = FirebaseFirestore.getInstance()
    var start: Long = 0
    var end: Long = 0
    var total: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_electronic, container, false)
        googleAnalytics.mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        googleAnalytics.trackScreen("electronics")

        getAllProducts()

        return root
    }

    override fun onDestroy() {
        end = Calendar.getInstance().timeInMillis
        total = end - start
        Log.e("time", "$total")
        googleAnalytics.time(total, "004", "electronics")
        super.onDestroy()
    }

    fun getAllProducts() {
        db!!.collection("electronics").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                Log.e("TAG", "${document.id} => ${document.data}")
                if (!querySnapshot.isEmpty) {
                    idProgressBar.setVisibility(View.GONE);
                    list.add(
                        product(
                            // document.data.get("image").toString().toInt(),
                            document.data.get("productName").toString(),
                            document.data.get("price").toString().toInt(),
                            document.data.get("details").toString()
                        )
                    )
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    val uAdapter = productAdapter(activity!!, list, this)
                    recyclerView.adapter = uAdapter
                } else {
                    Toast.makeText(activity, "No Item", Toast.LENGTH_LONG).show()
                }
            }
        }.addOnFailureListener { exception ->
            Log.e("TAG", "error...")
        }
    }

    override fun onClickItem(position: Int) {

        val i = Intent(activity, productDetail::class.java)
        //i.putExtra("image", list[position].image)
        i.putExtra("name", list[position].name)
        i.putExtra("price", list[position].price)
        i.putExtra("details", list[position].details)
        // googleAnalytics.trackScreen(list[position].name.toString())
        startActivity(i)

    }
}
