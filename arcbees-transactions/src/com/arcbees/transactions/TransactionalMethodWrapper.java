/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions;

import com.google.inject.Inject;
import com.googlecode.objectify.Objectify;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.inject.Named;
import javax.inject.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO refactor Objectify dependency out using the Unit Of Work pattern
public class TransactionalMethodWrapper implements MethodInterceptor {

    @Inject
    Logger logger;

    @Inject
    @Named("transactionWrapScope")
    SimpleScope scope;

    @Inject
    @TransactionalObjectify
    Provider<Objectify> objectifyProvider;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Objectify objectify = null;

        logger.info("entering scope");
        scope.enter();
        try {
            logger.info("injecting objectify instance");
            objectify = objectifyProvider.get();
            logger.info("objectify instance in wrapper: " + objectify.toString());
            logger.info("invoking method");
            return methodInvocation.proceed();
        } catch (Throwable throwable) {
            logger.log(Level.SEVERE, "Caught exception, rolling back transaction", throwable);
            if (objectify != null && objectify.getTxn().isActive()) {
                logger.info("rolling back transaction");
                objectify.getTxn().rollback();
            }
            throw throwable;
        } finally {
            if (objectify != null && objectify.getTxn().isActive()) {
                logger.info("committing transaction");
                objectify.getTxn().commit();
            }
            logger.info("exiting scope");
            scope.exit();
        }
    }

}
