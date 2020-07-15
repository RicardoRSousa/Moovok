package com.ricardojrsousa.movook.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("Exception HANDLER", "${throwable.localizedMessage} at \n ${throwable.printStackTrace()}")
    }

    val coroutineScope = CoroutineScope(Dispatchers.IO + exceptionHandler)

}