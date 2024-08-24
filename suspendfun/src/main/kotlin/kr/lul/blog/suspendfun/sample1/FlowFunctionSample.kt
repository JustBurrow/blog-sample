package kr.lul.blog.suspendfun.sample1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant

class FlowFunctionSample {
    /**
     * 한 번 호출하면 한번 [Instant]의 [Flow]를 반환하는 메서드.
     */
    fun call(): Flow<Instant> = flow {
        delay(2_000)
        emit(Instant.now())
    }
}