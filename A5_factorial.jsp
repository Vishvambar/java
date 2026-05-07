<%-- Place this file in: src/main/webapp/ (or WebContent/) alongside index.html --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Factorial Result</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .error { color: red; }
    </style>
</head>
<body>
    <h2>Factorial Result</h2>

    <%
        String numStr = request.getParameter("number");
        try {
            int n = Integer.parseInt(numStr.trim());

            if (n < 0) {
                out.println("<p class='error'>Error: Factorial is not defined for negative numbers.</p>");
            } else {
                long factorial = 1;
                for (int i = 2; i <= n; i++) {
                    factorial *= i;
                }
                out.println("<p>" + n + "! = <strong>" + factorial + "</strong></p>");
            }

        } catch (NumberFormatException e) {
            out.println("<p class='error'>Error: '" + numStr + "' is not a valid integer.</p>");
        } catch (Exception e) {
            out.println("<p class='error'>Unexpected error: " + e.getMessage() + "</p>");
        }
    %>

    <br><a href="index.html">&larr; Back</a>
</body>
</html>
