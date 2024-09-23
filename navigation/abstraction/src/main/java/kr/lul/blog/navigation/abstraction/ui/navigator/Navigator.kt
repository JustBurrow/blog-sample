package kr.lul.blog.navigation.abstraction.ui.navigator

import android.net.Uri

interface Navigator {
    /**
     * 현재 화면의 내비게이션 그래프 정보.
     */
    val destination: Destination

    /**
     * 돌아가기.
     */
    fun back()

    /**
     * 앱 재시작.
     */
    fun restart()

    /**
     * 앱 종료.
     */
    fun exit()

    /**
     * 프로세스는 종료하지 않고 UI 재시작.
     */
    fun reopen()

    /**
     * 앱 정보 화면 열기.
     */
    fun settings()

    /**
     * 웹 브라우저로 URL 열기.
     */
    fun web(url: Uri)

    /**
     * 웹 브라우저로 URL 열기.
     */
    fun web(url: String) {
        web(Uri.parse(url))
    }

    /**
     * 전화 걸기.
     */
    fun call(phoneNumber: String) {
        call(Uri.parse("tel:$phoneNumber"))
    }

    /**
     * 전화 걸기.
     */
    fun call(phoneNumber: Uri)
}