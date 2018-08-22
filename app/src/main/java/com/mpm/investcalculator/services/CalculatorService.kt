package com.mpm.investcalculator.services

import com.mpm.investcalculator.models.InvestmentRequestModel
import com.mpm.investcalculator.models.InvestmentResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CalculatorService {

    val BASE_URL = "https://api-simulator-calc.easynvest.com.br/"

    fun subscribeForCDI(investmentRequestModel: InvestmentRequestModel,
                        successCallback: (response: InvestmentResponseModel) -> Unit,
                        errorCallback: () -> Unit) {

        val retrofit = getRetrofit().create(CalculatorServiceInterface::class.java)

        retrofit.simulateCDI(investmentRequestModel.investedAmount, investmentRequestModel.index, investmentRequestModel.rate, investmentRequestModel.isTaxFree, investmentRequestModel.maturityDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            successCallback(response)
                        },
                        { error ->
                            errorCallback() }
                )
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

}