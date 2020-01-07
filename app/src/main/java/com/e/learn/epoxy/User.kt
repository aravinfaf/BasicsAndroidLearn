package com.e.learn.epoxy

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.e.learn.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class User @JvmOverloads constructor(
        context:Context,
        attributeset:AttributeSet?=null,
        defStyle:Int=0
):ConstraintLayout(context,attributeset,defStyle) {

    private var rootLayout: ConstraintLayout? = null
    private var nameTextView: TextView? = null
    private var emailTextView: TextView? = null
    private var ageTextView: TextView? = null

    init {
        View.inflate(context, R.layout.item_view_setup,this)
        rootLayout=findViewById(R.id.itemLayout)
        nameTextView=findViewById(R.id.nameTextView)
        emailTextView=findViewById(R.id.emailTextView)
        ageTextView=findViewById(R.id.ageTextView)
    }

    @TextProp
    fun setName(name:CharSequence){
        nameTextView!!.text=name
    }
    @TextProp
    fun setEmail(email:CharSequence){
        emailTextView!!.text=email
    }
    @TextProp
    fun setAge(age:CharSequence){
        ageTextView!!.setText(age)
    }

    @CallbackProp
    fun setItemClicklistener(listener: OnClickListener?){
        rootLayout!!.setOnClickListener(listener)
    }

    @ModelProp
    fun setBackground(backround:Int){
        rootLayout!!.setBackgroundColor(backround)
    }
}