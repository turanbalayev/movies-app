package com.turanbalayev.moviesapp.util

class Validation {
    companion object {
        fun validateEmail(email: String): CustomError {
            return if (email.isEmpty()) {
                CustomError(message = "Email can not be empty!", hasError = true)
            } else if (!email.contains("@")) {
                CustomError(message = "Email must contain '@' sign.", hasError = true)
            } else {
                CustomError(message = "", hasError = false)
            }
        }


        fun validatePassword(password: String): CustomError {
            return if (password.isEmpty()) {
                CustomError(message = "Password can not be empty!", hasError = true)
            } else if (password.length < 6) {
                CustomError(message = "Password must be at least 6 characters!", hasError = true)
            } else {
                CustomError(message = "", hasError = false)
            }
        }
    }
}