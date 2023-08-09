package com.turanbalayev.moviesapp.util

class Validation {
    companion object {
        fun validateEmail(email: String): CustomValidationResponse {
            return if (email.isEmpty()) {
                CustomValidationResponse(message = "Email can not be empty!", hasError = true)
            } else if (!email.contains("@")) {
                CustomValidationResponse(message = "Email must contain '@' sign.", hasError = true)
            } else {
                CustomValidationResponse(message = "", hasError = false)
            }
        }


        fun validatePassword(password: String): CustomValidationResponse {
            return if (password.isEmpty()) {
                CustomValidationResponse(message = "Password can not be empty!", hasError = true)
            } else if (password.length < 6) {
                CustomValidationResponse(message = "Password must be at least 6 characters!", hasError = true)
            } else {
                CustomValidationResponse(message = "", hasError = false)
            }
        }
    }
}