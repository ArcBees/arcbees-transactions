/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions.demo;

import com.arcbees.transactions.TransactionsModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyService;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            public void configureServlets() {
                ObjectifyService.register(Sprocket.class);
                install(new TransactionsModule());
                serve("/").with(TransactionsDemo.class);
            }
        });
    }

}
