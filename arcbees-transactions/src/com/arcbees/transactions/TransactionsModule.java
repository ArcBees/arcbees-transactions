/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class TransactionsModule extends AbstractModule {

    @Override
    protected void configure() {
        SimpleScope transactionWrapScope = new SimpleScope();
        bindScope(TransactionWrapScoped.class, transactionWrapScope);
        bind(SimpleScope.class)
                .annotatedWith(Names.named("transactionWrapScope"))
                .toInstance(transactionWrapScope);

        TransactionalMethodWrapper transactionalMethodWrapper = new TransactionalMethodWrapper();
        requestInjection(transactionalMethodWrapper);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class),
                transactionalMethodWrapper);
    }

    @Provides
    @TransactionalObjectify
    @TransactionWrapScoped
    Objectify provideTransactionalObjectify() {
        return ObjectifyService.beginTransaction();
    }

}
