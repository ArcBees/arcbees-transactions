/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions.demo;

import com.arcbees.transactions.Transactional;
import com.googlecode.objectify.Objectify;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Singleton
public class TransactionsDemo extends HttpServlet {

    private final Logger logger;

    private final Provider<Objectify> objectifyProvider;

    // TODO find the right way to inject the Objectify instance, since we can't have it be a singleton

    @Inject
    public TransactionsDemo(final Logger logger, final Provider<Objectify> objectifyProvider) {
        this.logger = logger;
        this.objectifyProvider = objectifyProvider;
    }

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("entered method");

        Objectify objectify = objectifyProvider.get();

        if (objectify != null) {
            logger.info("objectify was initialized");
            logger.info("objectify instance in servlet: " + objectify.toString());
        }

//        Objectify objectify = ObjectifyService.factory().begin();
//
//        Query<Sprocket> query = objectify.query(Sprocket.class).filter("name", "Foobar").order("-name");
//        logger.info("Before executing get()");
//        Sprocket sprocket = query.get();
//        logger.info("After executing get()");
        throw new IOException("foobar");

        //logger.info("forwarding request");

        //request.getRequestDispatcher("transactionsDemo.jsp").forward(request, response);
    }

}
