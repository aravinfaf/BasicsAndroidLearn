package com.e.learn.litho.widgets;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.e.learn.R;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Progress;
import com.facebook.litho.widget.SolidColor;
import com.facebook.litho.widget.Spinner;
import com.facebook.litho.widget.Text;
import com.facebook.litho.widget.TextInput;

import java.util.ArrayList;
import java.util.List;

public class Widgets  extends AppCompatActivity {

    ComponentContext c;
    Component component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        c=new ComponentContext(Widgets.this);

        //TextView
//         component= Text.create(c).text("Aravindkumar Raj").textSizeDip(20).textColor(getResources().getColor(R.color.penRoyalBlue))
//                    .textAlignment(Layout.Alignment.ALIGN_CENTER).build();

        //Edittext
//         InputFilter inputFilter=new InputFilter.LengthFilter(20);
//         component= TextInput.create(c).textColorStateList(ColorStateList.valueOf(getResources().getColor(R.color.penRoyalBlue)))
//                 .multiline(true).inputFilter(inputFilter).backgroundColor(Color.GREEN)
//                 .hint("Enter Name")
//                 .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES).build();

        //ImageView
//        component= Image.create(c).drawableRes(R.drawable.diet).scaleType(ImageView.ScaleType.CENTER_CROP).build();

        //Solid Color
//        component= SolidColor.create(c).color(Color.YELLOW).alpha(5).build();

        //Progress
//        component= Progress.create(c).indeterminateDrawableRes(R.drawable.background).build();

        //Spinner
//        List<String> mystring=new ArrayList<>();
//        mystring.add("Aravind");
//        mystring.add("kumar");
//        mystring.add("Raj");
//
//        component= Spinner.create(c).options(mystring).selectedOption(mystring.get(0)).build();



        setContentView(LithoView.create(c,component));
    }
}
