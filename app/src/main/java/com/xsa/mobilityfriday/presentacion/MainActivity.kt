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

        fab.setOnClickListener {
            val intent = Intent(this, AgregarActivity::class.java)
            startActivity(intent)
        }

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
        superheros.add(Superhero("Spiderman", "Marvel", "Peter Parker", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/spiderman.jpg?alt=media&token=012b38fa-24a0-494c-8f59-7fe1c0f7dae7", "Gracias a su traje puede lanzar telarañas de las cuales se cuelga"))
        superheros.add(Superhero("Daredevil", "Marvel", "Matthew Michael Murdock", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/daredevil.jpg?alt=media&token=5994f4d1-8e59-44a4-a72c-9791e0e9c14c", "Está ciego pero puede ver más que el resto"))
        superheros.add(Superhero("Wolverine", "Marvel", "James Howlett", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/logan.jpeg?alt=media&token=eb3895ac-ecba-47ee-a24b-3b9f67d4c7bc", "Su esqueleto está formado de adamamtium, además se regenera"))
        superheros.add(Superhero("Batman", "DC", "Bruce Wayne", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/batman.jpg?alt=media&token=96a3c5c9-06d7-4362-8868-fc131ebb1528", "Mataron a sus padres y desde entonces busca venganza en Gotham"))
        superheros.add(Superhero("Thor", "Marvel", "Thor Odinson", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/thor.jpg?alt=media&token=b20dc66b-be40-4529-9a85-2dcc97c5c5c9", "Un dios con un martillo"))
        superheros.add(Superhero("Flash", "DC", "Jay Garrick", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/flash.png?alt=media&token=d58a61c1-8160-49c9-b3a9-a58437cbcf63", "Correo mucho, tanto que ni le ves"))
        superheros.add(Superhero("Green Lantern", "DC", "Alan Scott", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/green-lantern.jpg?alt=media&token=e154d850-3897-4350-b3a3-110657a66b70", "Tiene una linterna verde, pero no sé para que la necesita"))
        superheros.add(Superhero("Wonder Woman", "DC", "Princess Diana", "https://firebasestorage.googleapis.com/v0/b/notablenotes-28616.appspot.com/o/wonder_woman.jpg?alt=media&token=300d9e5f-26bf-4ce7-986e-74d7fc66c4c8", "La única superheroína y tiene nombre de princesa real"))
        return superheros
    }
}

data class Superhero(
        var superhero: String,
        var publisher: String,
        var realName: String,
        var photo: String,
        var description: String
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
            intent.putExtra("DESCRIPCION", superhero.description)
            intent.putExtra("AVATAR", superhero.photo)
            startActivity(context, intent, null)
            Toast.makeText(context, "DESCRIPCION DE: " + superhero.superhero, Toast.LENGTH_SHORT).show()
        }
    }
}