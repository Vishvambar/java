/*
 * ============================================================
 *  ECLIPSE SETUP — Dynamic Web Project
 * ============================================================
 *
 *  1. File → New → Dynamic Web Project → set Target Runtime to Tomcat
 *  2. Place index_A4.html as "index.html" in:
 *       → src/main/webapp/index.html   (newer Eclipse)
 *       → WebContent/index.html        (older Eclipse)
 *  3. Place THIS file as "A4_AreaServlet.java" in:
 *       → src/main/java/               (newer Eclipse)
 *       → Java Resources/src/          (older Eclipse)
 *  4. Right-click project → Run As → Run on Server
 *  5. Open: http://localhost:8080/YourProjectName/index.html
 *
 * ============================================================
 *  IMPORTANT — CHECK YOUR TOMCAT VERSION:
 *    Tomcat 9  → use javax.servlet.*  (lines below are correct)
 *    Tomcat 10 → change javax to jakarta on lines 22-27
 * ============================================================
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// *** FOR TOMCAT 10+, REPLACE the 5 javax imports above with: ***
// import java.io.IOException;
// import java.io.PrintWriter;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;
// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

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
