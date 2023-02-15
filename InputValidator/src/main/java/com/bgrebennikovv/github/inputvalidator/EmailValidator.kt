package com.bgrebennikovv.github.inputvalidator

data class EmailParams(
    var allowedDomains : List<String> = listOf()
)

class EmailValidator(
    private val email: String
) : ValidationResult()  {

    private var emailParams = EmailParams()
    private val emailDomain = try {
        email.split("@")[1]
    } catch (e: java.lang.Exception){
        null
    }

    private fun hasErrors() : String?{

        with(emailParams){
            if (emailDomain == null) return setError("Invalid email")
            if(!allowedDomains.contains(emailDomain)) return setError("Domain not allowed")

        }
        return null
    }

    infix fun onError(err: (String) -> Unit){
        hasErrors()?.let{
            err.invoke(it)
        }
    }


    infix fun params(params: EmailParams.() -> Unit) = this.apply {
        emailParams.apply(params)
    }


}