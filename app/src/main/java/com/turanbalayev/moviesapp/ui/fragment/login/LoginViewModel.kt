package com.turanbalayev.moviesapp.ui.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turanbalayev.moviesapp.data.repository.AuthRepository
import com.turanbalayev.moviesapp.util.NetworkResult
import com.turanbalayev.moviesapp.util.CustomAuthInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {


    private val _authResult = MutableLiveData<CustomAuthInfo>()
    val authResult: LiveData<CustomAuthInfo> get() = _authResult

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


/*    fun loginUser(email:String,password: String){
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    _authResultStr.value = "You signed in successfully!"

                } else {
                    _authResultStr.value = it.exception?.localizedMessage?.toString() ?: "Operation failed! Try again."
                }
            }
        }
    }*/


    fun loginUser(email:String,password: String){
        viewModelScope.launch {
            _loading.value = true
            when(val result = authRepository.loginUserRP(email,password)){
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