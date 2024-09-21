package kr.lul.blog.navigation.guide.ui

import androidx.navigation.NavController

fun NavController.first() {
    popBackStack()
    navigate("first")
}

fun NavController.second() {
    navigate("second")
}

fun NavController.third(param1: Int) {
    navigate("third/$param1")
}

fun NavController.third(param1: Int, param2: String) {
    navigate("third/$param1?param2=$param2")
}