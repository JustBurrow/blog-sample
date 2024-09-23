package kr.lul.blog.navigation.scope.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    companion object {
        private const val TAG = "SplashViewModel"
    }

    init {
        Log.i(TAG, "#init called.")
    }

    override fun toString() = listOf<String>(
    ).joinToString(", ", "$TAG(", ")")
}