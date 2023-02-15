package com.bgrebennikovv.github.inputvalidator

data class PasswordParams(
    var minSize : Int = 8,
    var maxSize: Int = 128
)

class PasswordValidator(
    private val password: String
) : ValidationResult() {

    var passwordParams = PasswordParams()

    private fun hasErrors() : String?{

        with(passwordParams){

            if(password.length < minSize || password.length > maxSize) return setError("Password must have more 12 symbols")

        }
        return null
    }

    infix fun onError(err: (String) -> Unit){
        hasErrors()?.let{
            err.invoke(it)
        }
    }


    infix fun params(params: PasswordParams.() -> Unit) = this.apply {
        passwordParams.apply(params)
    }


}