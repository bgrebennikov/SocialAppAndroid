package com.bgrebennikovv.github.inui.DefaultStateButton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.bgrebennikovv.github.inui.R
import com.bgrebennikovv.github.inui.databinding.DefaultStateButtonBinding

class DefaultStateButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr : Int = 0
) : RelativeLayout(
    context, attrs, defStyleAttr
) {

    private val binding: DefaultStateButtonBinding by lazy {
        DefaultStateButtonBinding.inflate(LayoutInflater.from(context), this, true)
    }

    init {


        val attrsData = context.obtainStyledAttributes(
            attrs, R.styleable.DefaultStateButton,
            defStyleAttr,
            0
        )

        val text = attrsData.getString(R.styleable.DefaultStateButton_text) ?: ""
        val layoutWidth = attrsData.getLayoutDimension(R.styleable.DefaultStateButton_android_layout_width,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT)
        val layoutHeight = attrsData.getLayoutDimension(R.styleable.DefaultStateButton_android_layout_height,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.apply {
            container.layoutParams = LayoutParams(
                layoutWidth, layoutHeight
            )
        }

        setBtnText(text)

        attrsData.recycle()
    }

    private fun setBtnText(text: String){
        binding.text.text = text
    }

    fun setButtonState(state: ButtonStates){
        when(state){
            ButtonStates.DEFAULT -> binding.apply {
                isClickable = true
                text.visibility = View.VISIBLE
                drawable.visibility = View.VISIBLE
                progressCircular.visibility = View.INVISIBLE
            }
            ButtonStates.LOADING -> binding.apply {
                isClickable = false
                text.visibility = View.INVISIBLE
                drawable.visibility = View.INVISIBLE
                progressCircular.visibility = View.VISIBLE
            }
        }
    }
}