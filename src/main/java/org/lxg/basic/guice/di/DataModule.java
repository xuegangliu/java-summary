package org.lxg.basic.guice.di;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.lxg.basic.guice.di.base.DataProcessor;

public class DataModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(DataProcessor.class).to(StringDataConcatenation.class);
        bind(DataProcessor.class).toProvider(TestProviders.class);
        bind(int.class).toInstance(10);
        bind(String.class).toInstance("--|--");
        bind(String.class).annotatedWith(NotLeaveString.class).toInstance("Changed Annotated String");
        bind(String.class).annotatedWith(Names.named("Not leave string")).toInstance("Changed Named String");

    }
/**
    @Provides
    DataProcessor getProcessor() {
        StringDataConcatenation processor = new StringDataConcatenation();
        processor.setTemplate("\n\t\tstr1 = %s\n\t\tstr2 = %s");
        return processor;
    }
    **/
}