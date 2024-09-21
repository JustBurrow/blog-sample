package kr.lul.blog.navigation.guide.ui

import androidx.navigation.NavController

fun NavController.first() {
    popBackStack()
    navigate("first")
}

fun NavController.second() {
    navigate("second")
}

fun NavController.third() {
    navigate("third")
}