package webapp.main.srmhosteloutpass;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/displayOutpass")
public class DisplayOutpassServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            res.getWriter().print("not_logged_in");
            return;
        }

        int studentId = (int) session.getAttribute("studentId");
        JSONArray jsonArray = new JSONArray();

        try (Connection conn = DBConnector.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM outpass_requests WHERE student_id=?"
            );
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("reason", rs.getString("reason"));
                obj.put("fromDate", rs.getString("from_date"));
                obj.put("toDate", rs.getString("to_date"));
                obj.put("status", rs.getString("status"));
                jsonArray.put(obj);
            }

            res.setContentType("application/json");
            res.getWriter().print(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
