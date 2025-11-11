package webapp.main.srmhosteloutpass;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM students WHERE email=? AND password=?"
            );
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            res.setContentType("text/plain");
            PrintWriter out = res.getWriter();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("studentId", rs.getInt("id"));
                session.setAttribute("studentName", rs.getString("name"));
                out.print("success");
            } else {
                out.print("invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
