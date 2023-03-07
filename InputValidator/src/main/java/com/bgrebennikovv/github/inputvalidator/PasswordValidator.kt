package com.bgrebennikovv.github.inputvalidator

import android.text.Editable

data class PasswordParams(
    var minSize: Int = 8,
    var maxSize: Int = 128
)

class PasswordValidator(
    private val password: String?,
) : ValidationResult() {

    var passwordParams = PasswordParams()

    private fun hasErrors(): String? {

        if (password.isNullOrBlank()) return setError("Password must be not empty")

        with(passwordParams) {

            if (password.length < minSize || password.length > maxSize) return setError("Password must have more $minSize symbols")

        }
        return null
    }

    fun ifPasswordsNotIdentical(p1: Editable?, p2: Editable?, callback: () -> Unit){
        if(p1.toString() != p2.toString()) {
            setError("Passwords must be identical")
            callback.invoke()
        }
    }

    infix fun onError(err: (String) -> Unit) {
        hasErrors()?.let {
            err.invoke(it)
        }
    }


    infix fun params(params: PasswordParams.() -> Unit) = this.apply {
        passwordParams.apply(params)
    }


}