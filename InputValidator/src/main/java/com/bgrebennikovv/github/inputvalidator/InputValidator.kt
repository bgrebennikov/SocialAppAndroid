package com.bgrebennikovv.github.inputvalidator

class InputValidator() {

    init {
        clearErrors()
    }



    fun validateEmail(email: String?, l : EmailValidator.() -> Unit) : EmailValidator{
        return EmailValidator(email).apply(l)
    }

    fun validatePassword(password: String?, l : PasswordValidator.() -> Unit) : PasswordValidator{
        return PasswordValidator(password).apply(l)
    }

    fun validateText(text: String?, fieldName: String? = null, l: TextValidator.() -> Unit) : TextValidator{
        return TextValidator(text, fieldName).apply(l)
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
            clearErrors()
            return InputValidator().apply(v)
        }

        fun clearErrors(){
            errors.clear()
        }
    }

}