package kr.lul.blog.navigation.guide.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor() : ViewModel() {
    companion object {
        private const val TAG = "ThirdViewModel"
    }

    init {
        Log.i(TAG, "#init called.")
    }

    override fun toString() = listOf<String>(
    ).joinToString(", ", "$TAG(", ")")
}