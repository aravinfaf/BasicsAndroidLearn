package com.e.learn.litho;

import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import com.facebook.litho.widget.Text;

import java.util.List;

@GroupSectionSpec
public class FooSectionSpec {

    @OnCreateChildren
    static Children onCreateChildren(
            SectionContext c,
            @Prop String headerTitle,
            @Prop List<String> data) {

        return Children.create()
                .child(
                        SingleComponentSection.create(c)
                                .component(
                                        Text.create(c)
                                                .text(headerTitle)
                                                .build()))
                .child(
                        DataDiffSection.<String>create(c)
                                .data(data)
                                .renderEventHandler(FooSection.onRender(c)))
                .build();
    }

    @OnEvent(RenderEvent.class)
    static RenderInfo onRender(
            SectionContext c,
            @FromEvent String model) {
        return ComponentRenderInfo.create()
                .component(
                        Text.create(c)
                                .text(model)
                                .build())
                .build();
    }
}
