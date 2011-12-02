/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions.demo;

import com.googlecode.objectify.annotation.Entity;

import javax.persistence.Id;

@Entity
public class Sprocket {

    @Id
    Long id;

    String name;

    @SuppressWarnings("unused")
    protected Sprocket() {
    }

    public Sprocket(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
