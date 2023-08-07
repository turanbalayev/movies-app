package com.turanbalayev.moviesapp.ui.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val auth: FirebaseAuth) : ViewModel() {
    private val _authResultStr = MutableLiveData<String>()
    val authResultStr: LiveData<String> get() = _authResultStr


    fun loginUser(email:String,password: String){
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    _authResultStr.value = "You signed in successfully!"

                } else {
                    _authResultStr.value = "Operation failed! Try again."
                }
            }
        }
    }
}