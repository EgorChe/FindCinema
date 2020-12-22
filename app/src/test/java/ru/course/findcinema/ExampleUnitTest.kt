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
    fun coroutinesTest() {
        runBlocking {
            Presenter().launch { }
            val job = launch {
                testFunc()
            }
//            val result: Deferred<String> = async {
//                "world"
//            }
            print("world")
//            print(result.await())
        }
    }

    suspend fun testFunc() {
        delay(1000)
        print("Hello ")
    }
}

class Presenter : CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Job()

    fun test() {
        job = launch {

        }
    }

    fun onCancel() {
        job.cancel()
    }

    fun onDestroy() {
        cancel()
    }

}