package yongjun.sideproject.ui.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

sealed class Async<out T>(private val value: T?) {

    open operator fun invoke(): T? = value
}

data object Uninitialized : Async<Nothing>(null)

data class Loading<out T>(private val value: T? = null) : Async<T>(value)

data class Success<out T>(private val value: T) : Async<T>(value) {

    override fun invoke(): T = value
}

data class Fail<out T>(val error: Exception, private val value: T? = null) : Async<T>(value)

fun <T> (suspend () -> T).execute(
    coroutineScope: CoroutineScope,
    getRetainedValue: (() -> T?) = { null },
    reducer: (Async<T>) -> Unit,
): Job {
    reducer(Loading(getRetainedValue()))
    return coroutineScope.launch {
        try {
            val result = invoke()
            reducer(Success(result))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            reducer(Fail(e, getRetainedValue()))
        }
    }
}
