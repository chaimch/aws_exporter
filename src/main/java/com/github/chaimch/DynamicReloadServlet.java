package com.github.chaimch;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DynamicReloadServlet extends HttpServlet {
    private static CloudWatchCollector collector;

    public DynamicReloadServlet(CloudWatchCollector collector) {
        this.collector = collector;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(405);
        resp.setContentType("text/plain");
        resp.getWriter().print("Only POST requests allowed");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        collector.reloadConfig();

        resp.setContentType("text/plain");
        resp.getWriter().print("OK");
    }
}
