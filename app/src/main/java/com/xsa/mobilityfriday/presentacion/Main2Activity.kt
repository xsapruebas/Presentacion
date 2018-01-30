package com.xsa.mobilityfriday.presentacion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        ivAvatar.loadUrl(intent.getStringExtra("AVATAR"))
        tvDescripcion.text = intent.getStringExtra("DESCRIPCION")
    }
    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }
}
