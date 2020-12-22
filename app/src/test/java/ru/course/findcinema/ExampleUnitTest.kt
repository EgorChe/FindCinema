package ru.course.findcinema

import kotlinx.coroutines.*
import org.junit.Test

import kotlin.coroutines.CoroutineContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun maim() {

        runBlocking {
            ScopeHolder().apply {
                val data1 = async {
                    getHelloString()
                }
                val data2 = async {
                    delay(1500L)
                    "World!"
                }
                println(data1.await() + data2.await())
            }
        }
    }
}

suspend fun getHelloString(): String {
    delay(1000L)
    return "Hello,"
}

class ScopeHolder : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() /*+ Dispatchers.Main*/
}