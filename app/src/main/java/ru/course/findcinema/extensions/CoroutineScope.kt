package ru.course.findcinema.extensions

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithErrorHandler(
    block: suspend () -> Unit,
    onError: (Throwable) -> Unit,
) {
    launch(CoroutineExceptionHandler { _, exception ->
        onError(exception)
        Log.e("launchWithErrorHandler", exception.message, exception)
    }) {
        block()
    }
}