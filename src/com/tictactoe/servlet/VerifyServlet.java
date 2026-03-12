package com.tictactoe.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import com.tictactoe.util.DBConnection;

public class VerifyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String userOtp = request.getParameter("otp");
        String realOtp = (String) session.getAttribute("otp");

        if (userOtp.equals(realOtp)) {

            try {

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "insert into users(name,email,phone,password,verified) values(?,?,?,?,1)");

                ps.setString(1, (String) session.getAttribute("name"));
                ps.setString(2, (String) session.getAttribute("email"));
                ps.setString(3, (String) session.getAttribute("phone"));
                ps.setString(4, (String) session.getAttribute("password"));

                ps.executeUpdate();

                response.sendRedirect("login.jsp");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            response.getWriter().println("Invalid OTP");

        }

    }
}