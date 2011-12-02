/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import javax.inject.Provider;

public class ObjectifyProvider implements Provider<Objectify> {

    @Override
    public Objectify get() {
        return ObjectifyService.beginTransaction();
    }

}
