package com.example.crypto.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crypto.pojo.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT* FROM full_price_list ORDER BY lastUpdate desc")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>
    @Query("SELECT * From full_price_list where fromSymbol == :fSym limit 1")
    fun getPriceInfoAboutCoin(fSym:String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}