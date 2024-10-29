package com.example.crypto

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.crypto.adapters.CoinInfoAdapter
import com.example.crypto.databinding.ActivityCoinPriceListBinding
import com.example.crypto.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private var binding: ActivityCoinPriceListBinding? = null

    private val myLog = "MyLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding?.rvCoinPriceList?.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
            Log.d(myLog, "Success in activity: $it")
        })
        viewModel.priceList.observe(this, Observer {
            if (it.isNotEmpty()) {
                adapter.coinInfoList = it
                binding?.progressBar?.visibility = View.GONE // Скрываем индикатор загрузки
            } else {
                binding?.progressBar?.visibility = View.VISIBLE // Показываем индикатор загрузки
            }
        })
    }
}