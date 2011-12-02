/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions;

import com.google.inject.ScopeAnnotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

@Target({TYPE, METHOD})
@Retention(RUNTIME)
@ScopeAnnotation
public @interface TransactionWrapScoped {
}
