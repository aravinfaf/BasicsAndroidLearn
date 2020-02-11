package com.e.learn.litho;

import android.graphics.Color;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.sections.widget.ListRecyclerConfiguration;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import java.util.ArrayList;
import java.util.List;
import static com.facebook.litho.widget.SnapUtil.SNAP_TO_CENTER;

@GroupSectionSpec
public class ListSectionSpec {

//    @OnCreateChildren
//    static Children onCreateChildren1(final SectionContext c) {
//        Children.Builder builder = Children.create();
//
//        for (int i = 0; i < 32; i++) {
//            builder.child(
//                    SingleComponentSection.create(c)
//                            .key(String.valueOf(i))
//                            .component(ListItem.create(c)
//                                    .color(i%2==0?Color.BLUE:Color.GREEN)
//                                    .title(i+" AK")
//                                    .subtitle("Android Developer")
//                                    .build()));
//
//
//        }
//        return builder.build();
//    }

    private static List<Integer> generateData(int count){

        List<Integer> data=new ArrayList<>();
        for(int i=0;i<count;i++){
            data.add(i);
        }
        return data;
    }

    @OnEvent(RenderEvent.class)
    static RenderInfo onRender(SectionContext c, @FromEvent Integer model){


        return ComponentRenderInfo.create()
                .component(ListItem.create(c).color(model%2==0?Color.TRANSPARENT:Color.GREEN)
                                                .title(model+".Aravind")
                                                .subtitle("Android Developer")
                                                .build())
                .build();

    }

    @OnCreateChildren
    static Children onCreateChildren(SectionContext c){
        return Children.create()
                .child(
                        SingleComponentSection.create(c)
                            .component(
                                    RecyclerCollectionComponent.create(c)
                                    .disablePTR(true)
                                    .recyclerConfiguration(new ListRecyclerConfiguration(LinearLayoutManager.HORIZONTAL,false,SNAP_TO_CENTER))
                            .section(
                                    DataDiffSection.create(c)
                                    .data(generateData(32))
                                    .renderEventHandler(ListSection.onRender(c))
                                    .build())
                                    .canMeasureRecycler(true)
                            ).build()).child(

                        DataDiffSection.<Integer>create(c)
                                .data(generateData(32))
                                .renderEventHandler(ListSection.onRender(c)))
                .build();
    }
}

