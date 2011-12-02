/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.impl.ObjectifyImpl;

public class TransactionsModule extends AbstractModule {

    @Override
    protected void configure() {
        SimpleScope transactionWrapScope = new SimpleScope();
        bindScope(TransactionWrapScoped.class, transactionWrapScope);
        bind(SimpleScope.class)
                .annotatedWith(Names.named("transactionWrapScope"))
                .toInstance(transactionWrapScope);

        bind(Objectify.class).toProvider(ObjectifyProvider.class).in(TransactionWrapScoped.class);

        TransactionalMethodWrapper transactionalMethodWrapper = new TransactionalMethodWrapper();
        requestInjection(transactionalMethodWrapper);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class),
                transactionalMethodWrapper);
    }

}
