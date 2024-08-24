package kr.lul.blog.suspendfun.sample1

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import java.time.Instant

fun main(vararg args: String) {
    val suspendTarget = SuspendFunctionSample()
    val flowTarget = FlowFunctionSample()

    runBlocking {
        println("start   : ${Instant.now()}")
        println("suspend : ${suspendTarget.call()}")
        println("flow    : ${flowTarget.call().first()}")
    }
}
