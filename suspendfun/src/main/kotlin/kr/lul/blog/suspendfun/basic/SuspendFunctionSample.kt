package kr.lul.blog.suspendfun.basic

import kotlinx.coroutines.delay
import java.time.Instant

class SuspendFunctionSample {
    /**
     * 한 번 호출하면 한번 [Instant]를 반환하는 메서드.
     */
    suspend fun call(): Instant {
        delay(2_000)
        return Instant.now()
    }
}