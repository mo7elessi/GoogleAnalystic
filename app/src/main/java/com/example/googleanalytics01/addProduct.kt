package com.example.googleanalytics01

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.android.synthetic.main.fragment_add_product.view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.util.*

class addProduct : Fragment() {
    private var db: FirebaseFirestore? = FirebaseFirestore.getInstance()
    private val PICK_IMAGE_REQUEST = 1
    var filepath: Uri? = null
    var storageReference: StorageReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_add_product, container, false)
        storageReference = FirebaseStorage.getInstance().getReference();
        root.addNewProduct.setOnClickListener {
            if (root.productName.text.toString().isEmpty()
                || root.price.text.toString().isEmpty()
                || root.details.text.toString().isEmpty()

            ) {
                Toast.makeText(activity, "Please fill fields ...", Toast.LENGTH_SHORT).show()
            } else {
                // uploadImage()
                saveToFirebase()

            }

        }
        root.chooseImage.setOnClickListener {
            //   pickImage()
        }
        return root
    }

    private fun saveToFirebase() {
        var pathName = ""
        if (radio0.isChecked) {
            pathName = "food"
        } else if (radio1.isChecked) {
            pathName = "clothes"
        } else {
            pathName = "electronics"
        }
        val product: MutableMap<String, Any> = HashMap()
        product["productName"] = productName.text.toString()
        product["price"] = price.text.toString()
        product["details"] = details.text.toString()
        //   product["image"] = filepath.toString()

        val pd = ProgressDialog(activity)
        pd.setMessage("Saving Data...")
        pd.show()

        db!!.collection(pathName).add(product).addOnSuccessListener { documentReference ->
            Log.e("TAG", "added Successfully...")
            pd.dismiss()
        }.addOnFailureListener {
            Log.e("TAG", "added Failed...")
            pd.dismiss()
        }

        productName.text.clear()
        price.text.clear()
        details.text.clear()
    }

    /*
        private fun pickImage() {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select an image"), PICK_IMAGE_REQUEST)
        }

        private fun uploadImage() {

            if (filepath != null) {
                val pd = ProgressDialog(activity)
                pd.setMessage("Loading...")
                pd.show()
                val ref = storageReference!!.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filepath!!)
                    .addOnSuccessListener {
                        Log.e("TAG", "Uploaded...")
                        pd.dismiss()
                    }
                    .addOnFailureListener { e ->
                        Log.e("TAG", "Failed...")
                    }
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
                filepath = data.data
                imageView.setImageURI(filepath)
            }
        }
    }


     */

     */


}