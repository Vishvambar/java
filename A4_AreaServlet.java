// Place this file in:  src/main/java/  (Dynamic Web Project)
// Place A4_index.html as index.html in:  src/main/webapp/  (or WebContent/)

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// NOTE: For Tomcat 10+, replace javax.servlet.* with jakarta.servlet.*

@WebServlet("/AreaServlet")
public class A4_AreaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String radiusStr = request.getParameter("radius");

            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Area Result</title></head><body>");

            try {
                double radius = Double.parseDouble(radiusStr);
                double area = Math.PI * radius * radius;

                out.println("<h2>Result</h2>");
                out.printf("<p>Radius = %.2f</p>%n", radius);
                out.printf("<p>Area = &pi; &times; r&sup2; = <strong>%.4f</strong></p>%n", area);

            } catch (NumberFormatException e) {
                out.println("<h2 style='color:red;'>Error</h2>");
                out.println("<p>Invalid radius. Please enter a valid number.</p>");
            }

            out.println("<br><a href='index.html'>&#8592; Back</a>");
            out.println("</body></html>");
        }
    }
}
