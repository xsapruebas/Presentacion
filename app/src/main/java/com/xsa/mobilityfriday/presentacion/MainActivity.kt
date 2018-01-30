package com.xsa.mobilityfriday.presentacion

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_superhero_list.view.*

class MainActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    val mAdapter: RecyclerAdapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
    }

    fun setUpRecyclerView() {
        mRecyclerView = rvSuperheroList as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(getSuperheros(), this)
        mRecyclerView.adapter = mAdapter
    }

    fun getSuperheros(): MutableList<Superhero> {
        var superheros: MutableList<Superhero> = ArrayList()
        superheros.add(Superhero("Spiderman", "Marvel", "Peter Parker", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg"))
        superheros.add(Superhero("Daredevil", "Marvel", "Matthew Michael Murdock", "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"))
        superheros.add(Superhero("Wolverine", "Marvel", "James Howlett", "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"))
        superheros.add(Superhero("Batman", "DC", "Bruce Wayne", "https://cursokotlin.com/wp-content/uploads/2017/07/batman.jpg"))
        superheros.add(Superhero("Thor", "Marvel", "Thor Odinson", "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"))
        superheros.add(Superhero("Flash", "DC", "Jay Garrick", "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"))
        superheros.add(Superhero("Green Lantern", "DC", "Alan Scott", "https://cursokotlin.com/wp-content/uploads/2017/07/green-lantern.jpg"))
        superheros.add(Superhero("Wonder Woman", "DC", "Princess Diana", "https://cursokotlin.com/wp-content/uploads/2017/07/wonder_woman.jpg"))
        return superheros
    }
}

data class Superhero(
        var superhero: String,
        var publisher: String,
        var realName: String,
        var photo: String
)

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var superheros: MutableList<Superhero> = ArrayList()
    lateinit var context: Context

    fun RecyclerAdapter(superheros: MutableList<Superhero>, context: Context) {
        this.superheros = superheros
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_superhero_list, parent, false))
    }

    override fun getItemCount(): Int {
        return superheros.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val superheroName = view.tvSuperhero
        val realName = view.tvRealName
        val publisher = view.tvPublisher
        val avatar = view.ivAvatar

        fun bind(superhero: Superhero, context: Context) {
            Log.i("SUPERHERO", "SUPERHERO: " + superhero.superhero)
            Log.i("SUPERHERO", "NOMBRE REAL: " + superhero.realName)
            Log.i("SUPERHERO", "COMPAÑIA: " + superhero.publisher)
            superheroName.setText(superhero.superhero)
            realName.setText(superhero.realName)
            publisher.setText(superhero.publisher)
            avatar.loadUrl(superhero.photo)

            itemView.setOnClickListener { abrirTarjeta(context, superhero) }
        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }

        fun abrirTarjeta(context: Context, superhero: Superhero) {
            val intent = Intent(context, Main2Activity::class.java)
            startActivity(context, intent, null)
            Toast.makeText(context, "CAMBIO DE ACTIVIDAD", Toast.LENGTH_SHORT).show()
        }
    }
}