import kotlinx.coroutines.*

suspend fun main2() {
    for(i in 0..5) {
        delay(500)
        println(i)
    }
    println("Test message!")
}
suspend fun main3() {
    coroutineScope {
        launch {
            heavyWork()
        }
        println("Test message!")
    }
}

suspend fun heavyWork() {
    for(i in 0..5) {
        delay(500)
        println(i)
    }
}
suspend fun main4() {
    coroutineScope {
        val job: Job = launch {
            heavyWork()
        }
        delay(1000)
        println("Started!")
        job.join() // ждем завершение работы корутины
        println("Finished!")
    }
}
suspend fun main5() {
    coroutineScope {
        // просто создаю корутину, но не запускаю
        val job: Job = launch(start = CoroutineStart.LAZY) {
            heavyWork()
        }
        delay(1000)
        job.start() // запускаем корутину
        job.cancel() // отмена корутины
        job.join() // ждем завершения
        job.cancelAndJoin()
    }
}

suspend fun main() {
    coroutineScope {
        val result : Deferred<String> = async{
            getResult()
        }
        println("result : ${result.await()}")
    }
}
suspend fun getResult() : String {
    delay(1000)
    return "Result string!"
}