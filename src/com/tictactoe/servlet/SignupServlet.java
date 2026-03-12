package com.tictactoe.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import com.tictactoe.util.EmailUtil;

public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Generate 6 digit OTP
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

        // Create session
        HttpSession session = request.getSession();

        // Store data in session
        session.setAttribute("otp", otp);
        session.setAttribute("name", name);
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);
        session.setAttribute("password", password);

        try {

            // Send OTP email
            EmailUtil.sendOTP(email, otp);

            // Redirect to verification page
            response.sendRedirect("verify.jsp");

        } catch (Exception e) {

            e.printStackTrace();

            response.getWriter().println("Error sending OTP. Please try again.");

        }
    }
}