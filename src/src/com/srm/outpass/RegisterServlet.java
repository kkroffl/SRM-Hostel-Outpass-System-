import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String rId = req.getParameter("rId");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
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

            out.print("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
