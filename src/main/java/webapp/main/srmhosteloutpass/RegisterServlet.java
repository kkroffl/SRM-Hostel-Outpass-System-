package webapp.main.srmhosteloutpass;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name="register",value = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String rId = req.getParameter("rId");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
            System.out.printf("""
                    {
                       %s,%s,%s,%s
                    }
                    %n""",name,rId,email,password);

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement check = conn.prepareStatement("SELECT * FROM students WHERE email=?");
            check.setString(1, email);
            ResultSet rs = check.executeQuery();

            res.setContentType("text/plain");
            PrintWriter out = res.getWriter();

            if (rs.next()) {
                out.print("exists");
                return;
            }

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO students (rId, name, email, password) VALUES (?, ?, ?, ?)"
            );
            ps.setString(1, rId);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.executeUpdate();

            out.print("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
