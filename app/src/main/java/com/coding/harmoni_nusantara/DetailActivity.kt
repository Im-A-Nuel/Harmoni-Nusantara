package com.coding.harmoni_nusantara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_INSTRUMENT = "key_instrument"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val dataInstrument = intent.getParcelableExtra<Instrument>(KEY_INSTRUMENT)

        supportActionBar?.title = dataInstrument?.name

        val tvDetailName : TextView = findViewById(R.id.name_detail)
        val tvDetailDesc : TextView = findViewById(R.id.desc_detail)
        val ivDetailIMg : ImageView = findViewById(R.id.img_detail)
        val btn_share : Button = findViewById(R.id.action_share)

        tvDetailName.text = dataInstrument?.name
        tvDetailDesc.text = dataInstrument?.description
        Glide.with(this)
            .load(dataInstrument?.photo)
            .into(ivDetailIMg)
        btn_share.setOnClickListener{
            shareText("${dataInstrument?.name}\n${dataInstrument?.description}")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun shareText(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, "Share melalui"))
    }
}