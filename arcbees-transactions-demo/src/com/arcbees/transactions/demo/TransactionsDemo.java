/**
 * Copyright 2011 ArcBees Inc.  All rights reserved.
 */

package com.arcbees.transactions.demo;

import com.arcbees.transactions.Transactional;

import javax.inject.Inject;
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

    // TODO find the right way to inject the Objectify instance, since we can't have it be a singleton

    @Inject
    public TransactionsDemo(final Logger logger) {
        this.logger = logger;
    }

    @Override
    @Transactional
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("entered method");

        //Objectify objectify = objectifyProvider.get();

//        if (objectify != null) {
//            logger.info("objectify was initialized");
//        }

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
