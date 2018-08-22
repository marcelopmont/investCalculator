package com.mpm.investcalculator.services

import com.mpm.investcalculator.models.InvestmentResponseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CalculatorServiceInterface {

    @GET("calculator/simulate")
    fun simulateCDI(@Query("investedAmount") investedAmount: Double,
                    @Query("index") index: String,
                    @Query("rate") rate: Int,
                    @Query("isTaxFree") isTaxFree: Boolean,
                    @Query("maturityDate") maturityDate: String):
            Observable<InvestmentResponseModel>

}