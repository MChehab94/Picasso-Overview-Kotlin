package mchehab.com.picassooverview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {

    private val IMAGE_URL = "https://www.w3schools.com/w3css/img_lights.jpg"

    private var isImageDownloaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDownloadImage.setOnClickListener {
            downloadImage()
        }

        if(savedInstanceState != null){
            isImageDownloaded = savedInstanceState.getBoolean("isImageDownloaded")
            if(isImageDownloaded)
                downloadImage()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean("isImageDownloaded", isImageDownloaded)
    }

    private fun downloadImage(){
        isImageDownloaded = true
        progressBar.visibility = View.VISIBLE
        Picasso.with(this).load(IMAGE_URL).error(R.drawable.error).into(imageView, object: Callback{
            override fun onSuccess(){
                Log.d("success", "inside on Success")
                progressBar.visibility = View.GONE
            }

            override fun onError(){
                Log.d("error", "inside on error")
                progressBar.visibility = View.GONE
            }
        })
    }
}
