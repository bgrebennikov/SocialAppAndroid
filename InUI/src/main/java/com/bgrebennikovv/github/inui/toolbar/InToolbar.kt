package com.bgrebennikovv.github.inui.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.bgrebennikovv.github.inui.R
import com.bgrebennikovv.github.inui.databinding.InToolbarDefaultBinding

class InToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle : Int = 0
) : LinearLayout(context, attrs, defStyle){

    private val titleText: String?
    private val subtitleText: String?

    private var binding: InToolbarDefaultBinding

    init {

        binding = InToolbarDefaultBinding.bind(LayoutInflater.from(context).inflate(R.layout.in_toolbar_default, this, true))

        val attrsData = context.obtainStyledAttributes(
            attrs, R.styleable.InToolbar, defStyle, 0
        )

        titleText = attrsData.getString(R.styleable.InToolbar_title)?.also {
            setTitle(it)
        }
        subtitleText = attrsData.getString(R.styleable.InToolbar_subtitle)?.also {
            setSubtitle(it)
        }


        attrsData.recycle()
    }

    fun setTitle(title: String){
        binding.toolbarTitle.apply {
            text = title
            visibility = View.VISIBLE
        }
    }

    fun setSubtitle(subtitle: String){
        binding.toolbarSubtitle.apply {
            text = subtitle
            visibility = View.VISIBLE
        }
    }

    fun setOnBackPressAction(action: () -> Unit){
        binding.popBackBtn.setOnClickListener {
            action.invoke()
        }
    }


}