package com.bgrebennikovv.github.inputvalidator

class TextValidator(
    private val text: String?,
    private val fieldName: String? = "Field"
) : ValidationResult() {

    private fun hasErrors(): String? {

        if (text.isNullOrBlank()) return setError("${fieldName ?: "Field"} must be not empty")

        return null
    }

    infix fun onError(err: (String) -> Unit){
        hasErrors()?.let {
            err.invoke(it)
        }
    }

}