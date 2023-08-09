package com.turanbalayev.moviesapp.ui.fragment.createaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.turanbalayev.moviesapp.ui.fragment.login.LoginFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CreateAccountViewModel @Inject constructor (
    private val auth: FirebaseAuth,
    ): ViewModel() {

    private val _authResultStr = MutableLiveData<String>()
    val authResultStr:LiveData<String> get() = _authResultStr

    private val _hasRegistered = MutableLiveData<Boolean>()
    val hasRegistered:LiveData<Boolean> get() = _hasRegistered


    fun registerUser(email:String,password: String){
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    _authResultStr.value = "You signed up successfully!"
                    _hasRegistered.value = true
                } else {
                    _authResultStr.value = it.exception?.localizedMessage.toString() ?:"Operation failed! Try again."
                }
            }
        }
    }





}