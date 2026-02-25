package kr.lul.kmp_trouble_xcode_project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform