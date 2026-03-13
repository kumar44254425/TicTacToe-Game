package com.tictactoe.websocket;

import javax.websocket.server.ServerContainer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebSocketConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {

            ServerContainer container = (ServerContainer) sce.getServletContext()
                    .getAttribute("javax.websocket.server.ServerContainer");

            container.addEndpoint(GameSocket.class);

            System.out.println("WebSocket endpoint registered!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}