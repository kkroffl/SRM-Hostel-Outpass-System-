import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/applyOutpass")
public class ApplyOutpassServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            res.getWriter().print("not_logged_in");
            return;
        }

        int studentId = (int) session.getAttribute("studentId");
        String reason = req.getParameter("reason");
        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO outpass_requests (student_id, reason, from_date, to_date) VALUES (?, ?, ?, ?)"
            );
            ps.setInt(1, studentId);
            ps.setString(2, reason);
            ps.setString(3, fromDate);
            ps.setString(4, toDate);
            ps.executeUpdate();

            res.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
