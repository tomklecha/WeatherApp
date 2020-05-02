package com.tkdev.weatherapp.current.core.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface CoroutineDispatcherFactory {

    val IO: CoroutineContext

    val UI: CoroutineContext

}

class CoroutineDispatcherFactoryDefault : CoroutineDispatcherFactory {
    override val IO: CoroutineContext
        get() = Dispatchers.IO

    override val UI: CoroutineContext
        get() = Dispatchers.Main

}