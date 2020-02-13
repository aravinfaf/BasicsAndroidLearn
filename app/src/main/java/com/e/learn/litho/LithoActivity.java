package com.e.learn.litho;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;

public class LithoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_litho);

        ComponentContext c=new ComponentContext(LithoActivity.this);

//        Component component= Text.create(c)
//                .text("Aravind")
//                .textColor(getResources().getColor(R.color.colorPrimaryDark))
//                .textSizeDip(20)
//                .extraSpacingDip(10)
//                .build();
//
//
//        final LithoView lithoView = LithoView.create(
//                this,
//                ListItem.create(c).build());
//        setContentView(lithoView);


        Component component= RecyclerCollectionComponent.create(c).duplicateParentState(true)
                .section(FooSection.create(new SectionContext(c)).build())
                .build();

        setContentView(LithoView.create(c,component));
    }
}
