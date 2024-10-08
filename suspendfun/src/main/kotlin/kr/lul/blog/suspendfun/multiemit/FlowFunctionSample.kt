package kr.lul.blog.suspendfun.multiemit

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import kotlin.random.Random

class FlowFunctionSample {
    /**
     * 임의의 횟수만큼 2초 간격으로 [Instant]를 발행하는 [Flow]를 반환하는 메서드.
     */
    fun call(): Flow<Instant> = flow {
        val repeat = Random.nextInt(1, 10)
        println("#call : repeat=$repeat")
        repeat(repeat) {
            delay(2_000)
            val now = Instant.now()
            emit(now)
        }
    }
}