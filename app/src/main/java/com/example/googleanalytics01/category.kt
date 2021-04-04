package com.example.googleanalytics01

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category.view.*
import java.util.*

class category : Fragment() {
    val googleAnalytics = GoogleAnalytics()
    var start: Long = 0
    var end: Long = 0
    var total: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_category, container, false)
        googleAnalytics.mFirebaseAnalytics = FirebaseAnalytics.getInstance(activity!!)
        googleAnalytics.trackScreen("Category")



        root.btnAdd.setOnClickListener {
            goToScreen(addProduct())

        }
        root.btnFood.setOnClickListener {
            goToScreen(food())

        }
        root.btnClothes.setOnClickListener {
            goToScreen(clothes())

        }
        root.btnElectronic.setOnClickListener {
            goToScreen(electronic())
        }
        return root
    }

    override fun onDestroy() {
        end = Calendar.getInstance().timeInMillis
        total = end - start
        googleAnalytics.time(total,"001", "Category")
        super.onDestroy()
    }
    fun goToScreen(fragment: Fragment) {
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            fragment
        ).addToBackStack(null).commit()
    }


}
