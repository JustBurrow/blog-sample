package kr.lul.blog.suspendfun.multiemit

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main(vararg args: String) {
    val suspendFunctionSample = SuspendFunctionSample()
    val flowTarget = FlowFunctionSample()

    runBlocking {
        println("start   : ${Instant.now()}")
        println("suspend : ${suspendFunctionSample.call()}")
        flowTarget.call().onEach {
            println("flow    : $it")
        }.collect()
    }
}