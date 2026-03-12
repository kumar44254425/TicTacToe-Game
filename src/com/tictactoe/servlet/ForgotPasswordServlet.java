package com.tictactoe.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import com.tictactoe.util.DBConnection;
import com.tictactoe.util.EmailUtil;

public class ForgotPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "select * from users where email=?");

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String otp = String.valueOf((int) (Math.random() * 900000) + 100000);

                HttpSession session = request.getSession();

                session.setAttribute("resetOtp", otp);
                session.setAttribute("resetEmail", email);

                EmailUtil.sendOTP(email, otp);

                response.sendRedirect("verify.jsp");

            } else {

                response.getWriter().println("Email not registered");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}