package com.turanbalayev.moviesapp.ui.fragment.createaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.turanbalayev.moviesapp.data.repository.AuthRepository
import com.turanbalayev.moviesapp.util.NetworkResult
import com.turanbalayev.moviesapp.util.CustomAuthInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateAccountViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val authRepository: AuthRepository
) : ViewModel() {

    /*    private val _authResultStr = MutableLiveData<String>()
        val authResultStr:LiveData<String> get() = _authResultStr

        private val _hasRegistered = MutableLiveData<Boolean>()
        val hasRegistered:LiveData<Boolean> get() = _hasRegistered*/


    private val _authResult = MutableLiveData<CustomAuthInfo>()
    val authResult: LiveData<CustomAuthInfo> get() = _authResult

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    /*    fun registerUser(email:String,password: String){
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
        }*/

    fun registerUser(email:String,password:String){
        viewModelScope.launch {

            _loading.value = true
            when(val result = authRepository.registerUserRP(email,password)){
                is NetworkResult.Success -> {
                    _authResult.value = result.data
                    _loading.value = false
                }
                is NetworkResult.Error -> {
                    _error.value = result.message
                    _loading.value = false
                }
            }
        }
    }


}