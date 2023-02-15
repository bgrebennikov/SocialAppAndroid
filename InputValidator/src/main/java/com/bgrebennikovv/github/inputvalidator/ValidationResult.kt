package com.bgrebennikovv.github.inputvalidator

abstract class ValidationResult {

    fun setError(message: String) : String{
        InputValidator.errors.add(message)
        return message
    }

}