package com.bgrebennikovv.github.inui.alerts

import android.graphics.Color
import android.view.LayoutInflater
import com.bgrebennikovv.github.inui.databinding.InAlertDialogBinding

data class ViewContent(
    var title: String? = null,
    var textColor: Int = Color.BLACK,
    var backgroundColor: Int = Color.WHITE,
    var body: String? = null,
    var buttonText: String? = null,
    var onConfirm: InAlert.() -> Unit = {
        this.dismiss()
    }
)

class InAlert(
    private val inflater: LayoutInflater,
    private val viewContent: ViewContent = ViewContent(),
) : BaseAlertDialog<InAlertDialogBinding>(inflater, InAlertDialogBinding::inflate, viewContent.backgroundColor) {

    init {
        setAlertContent(viewContent)
    }


    private fun setAlertContent(content: ViewContent){
        with(content){
            view.alertTitle.text = title
            view.alertTitle.setTextColor(textColor)

            view.windowMessageBody.text = body
            view.windowMessageBody.setTextColor(textColor)

            view.closeBtn.text = buttonText
            view.closeBtn.setTextColor(backgroundColor)

            view.closeBtn.setOnClickListener { onConfirm.invoke(this@InAlert) }


        }
    }

    fun dismiss(){
        builder.dismiss()
    }

    fun show(){
        builder.show()
    }

    infix operator fun invoke(args: ViewContent.() -> Unit) = InAlert(
        inflater, viewContent.apply(args)
    )


}