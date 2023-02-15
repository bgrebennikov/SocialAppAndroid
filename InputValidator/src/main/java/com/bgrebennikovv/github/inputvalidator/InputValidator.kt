package com.bgrebennikovv.github.inputvalidator

class InputValidator() {

    init {
        clearErrors()
    }

    private fun clearErrors(){
        errors.clear()
    }

    fun validateEmail(email: String, l : EmailValidator.() -> Unit) : EmailValidator{
        return EmailValidator(email).apply(l)
    }

    fun validatePassword(password: String, l : PasswordValidator.() -> Unit) : PasswordValidator{
        return PasswordValidator(password).apply(l)
    }

    infix fun onSuccessCheck(lambda: () -> Unit){
        if(errors.isEmpty()) {
            clearErrors()
            lambda.invoke()
            return
        }
    }

    companion object{

        val errors = mutableListOf<String>()

        infix fun validate(v : InputValidator.() -> Unit) : InputValidator{
            return InputValidator().apply(v)
        }
    }

}