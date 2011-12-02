/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions.demo;

import com.arcbees.transactions.Transactional;
import com.arcbees.transactions.TransactionalObjectify;
import com.google.inject.Inject;
import com.googlecode.objectify.Objectify;

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

    @Inject
    public TransactionsDemo(final Logger logger, @TransactionalObjectify final Provider<Objectify> objectifyProvider) {
        this.logger = logger;
        this.objectifyProvider = objectifyProvider;
    }

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("entered method");

        Objectify objectify = objectifyProvider.get();

        if (objectify == null) {
            throw new ServletException("objectify was not initialized");
        }

        logger.info("objectify was initialized");
        logger.info("objectify instance in servlet: " + objectify.toString());

        Sprocket sprocket = new Sprocket("MySprocket");
        objectify.put(sprocket);

        //throw new IOException("testing to make sure exceptions cause a rollback");

        logger.info("forwarding request");

        request.getRequestDispatcher("transactionsDemo.jsp").forward(request, response);
    }

}
