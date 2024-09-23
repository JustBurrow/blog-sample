package kr.lul.blog.navigation.experience.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor() : ViewModel() {
    companion object {
        private const val TAG = "FirstViewModel"
    }

    init {
        Log.i(TAG, "#init called.")
    }

    override fun toString() = listOf<String>(
    ).joinToString(", ", "$TAG(", ")")
}