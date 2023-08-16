package com.turanbalayev.moviesapp.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.turanbalayev.moviesapp.util.NetworkResult
import com.turanbalayev.moviesapp.util.CustomAuthInfo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val auth: FirebaseAuth) {

    private val resultForLogin =
        MutableLiveData<NetworkResult<CustomAuthInfo>>(NetworkResult.Error("Operation failed! Try again."))
    private val resultForRegister =
        MutableLiveData<NetworkResult<CustomAuthInfo>>(NetworkResult.Error("Operation failed! Try again."))


    suspend fun registerUserRP(email: String, password: String): NetworkResult<CustomAuthInfo> {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                resultForRegister.value =
                    NetworkResult.Success(CustomAuthInfo(true, "You signed up successfully!"))
            } else {
                resultForRegister.value = NetworkResult.Error(
                    it.exception?.localizedMessage?.toString() ?: "Operation failed! Try again."
                )
            }
        }.await()

        return resultForRegister.value!!;

    }

    /*    suspend fun registerUserRP(email: String, password: String): NetworkResult<CustomAuthInfo> {

        var networkResult: NetworkResult<CustomAuthInfo> = NetworkResult.Error("Operation failed! Try again.")

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            networkResult = if (it.isSuccessful) {
                NetworkResult.Success(CustomAuthInfo(true, "You signe up successfully!"))
            } else {
                NetworkResult.Error(
                    it.exception?.localizedMessage?.toString() ?: "Operation failed! Try again."
                )
            }
        }.await()

        return networkResult;

    }*/


    /*    suspend fun loginUserRP(email: String, password: String): NetworkResult<CustomAuthInfo> {

            var networkResult: NetworkResult<CustomAuthInfo> = NetworkResult.Error("Operation failed! Try again.")

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                networkResult = if (it.isSuccessful) {
                    NetworkResult.Success(CustomAuthInfo(true, "You signed in successfully!"))
                } else {
                    NetworkResult.Error(
                        it.exception?.localizedMessage?.toString() ?: "Operation failed! Try again."
                    )
                }
            }.await()


            return networkResult;

        }*/


    suspend fun loginUserRP(
        email: String,
        password: String
    ): NetworkResult<CustomAuthInfo> {


        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                resultForLogin.value =
                    NetworkResult.Success(CustomAuthInfo(true, "You signed in successfully!"))
            } else {
                resultForLogin.value = NetworkResult.Error(
                    it.exception?.localizedMessage?.toString() ?: "Operation failed! Try again."
                )
            }
        }.await()

        return resultForLogin.value!!;


    }


}