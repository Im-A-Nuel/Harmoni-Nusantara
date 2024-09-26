package com.coding.harmoni_nusantara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvInstrument: RecyclerView
    private val list = ArrayList<Instrument>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvInstrument = findViewById(R.id.rv_instrument)
        rvInstrument.setHasFixedSize(true)

        list.addAll(getListInstrument())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListInstrument() : ArrayList<Instrument> {
        val dataName = resources.getStringArray(R.array.instrument_data)
        val dataDesc = resources.getStringArray(R.array.instrument_desc)
        val dataPhoto = resources.obtainTypedArray(R.array.instrument_photo)
        val listInstrument = ArrayList<Instrument>()
        for (i in dataName.indices) {
            val instrument = Instrument(dataName[i],dataDesc[i], dataPhoto.getResourceId(i, -1))
            listInstrument.add(instrument)
        }
        return listInstrument
    }

    private fun showRecyclerList() {
        rvInstrument.layoutManager = LinearLayoutManager(this)
        val listInstrumentProcess = ListInstrumentProcess(list)
        rvInstrument.adapter = listInstrumentProcess
    }
}