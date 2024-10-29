package com.example.crypto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.crypto.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private var binding: ActivityCoinDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: ""
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailsInfo(fromSymbol).observe(this, Observer {
            binding?.tvPrice?.text = it.price.toString()
            binding?.tvMinPrice?.text = it.lowDay
            binding?.tvMaxPrice?.text = it.highDay
            binding?.tvLastMarket?.text = it.lastMarket
            binding?.tvLastUpdate?.text = it.getFormattedTime()
            binding?.tvFromSymbol?.text = it.fromSymbol
            binding?.tvToSymbol?.text = it.toSymbol
            Picasso.get().load(it.getFullImageUrl()).into(binding?.ivLogoCoin)
        })
    }


    companion object {

        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}