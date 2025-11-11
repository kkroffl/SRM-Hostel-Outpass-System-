package webapp.main.srmhosteloutpass;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name="homepage",value = "/home_page")
public class HomePageServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/javascript");
        res.getWriter().println("""
                alert('You do understand something');
                window.href.location='index.html';
                """);
    }
}
