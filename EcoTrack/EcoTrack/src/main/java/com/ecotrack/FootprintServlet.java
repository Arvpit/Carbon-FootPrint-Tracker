package com.ecotrack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculateFootprint")
public class FootprintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data from the frontend
        double milesDriven = Double.parseDouble(request.getParameter("milesDriven"));
        double electricityUsed = Double.parseDouble(request.getParameter("electricityUsed"));
        double mealsConsumed = Double.parseDouble(request.getParameter("mealsConsumed"));

        // Calculate carbon footprint (simplified formula)
        double footprint = (milesDriven * 0.4) + (electricityUsed * 0.6) + (mealsConsumed * 2.5);

        // Send response back to the frontend
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Your Carbon Footprint</h2>");
        out.println("<p>Your carbon footprint is " + String.format("%.2f", footprint) + " kg CO2.</p>");
        out.println("<div class='tip'><h3>Tips to Reduce Your Footprint</h3>");
        out.println(generateTips(footprint) + "</div>");
    }

    private String generateTips(double footprint) {
        if (footprint > 50) {
            return "<p>🚗 <strong>Reduce Driving:</strong> Carpool, use public transportation, or switch to an electric vehicle.</p>"
                 + "<p>💡 <strong>Save Energy:</strong> Turn off lights and appliances when not in use, and switch to energy-efficient bulbs.</p>"
                 + "<p>🍴 <strong>Eat Sustainably:</strong> Reduce meat consumption and choose locally sourced, organic foods.</p>";
        } else if (footprint > 20) {
            return "<p>🚲 <strong>Bike or Walk:</strong> Use a bike or walk for short distances instead of driving.</p>"
                 + "<p>🌱 <strong>Go Green:</strong> Plant trees or support reforestation projects to offset your carbon emissions.</p>"
                 + "<p>♻️ <strong>Recycle:</strong> Recycle paper, plastic, and glass to reduce waste.</p>";
        } else {
            return "<p>🌟 <strong>Great Job!</strong> Your carbon footprint is below average. Keep up the good work!</p>"
                 + "<p>🌍 <strong>Spread Awareness:</strong> Encourage friends and family to reduce their carbon footprint.</p>";
        }
    }
}