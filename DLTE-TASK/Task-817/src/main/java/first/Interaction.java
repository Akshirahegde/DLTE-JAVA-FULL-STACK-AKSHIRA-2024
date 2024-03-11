package first;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/first/*")
@WebServlet(name="Interaction",value="/first/api/*")
public class Interaction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestInvestment = req.getParameter("investment");
        String requestReturn = req.getParameter("return");
        String requestPeriod = req.getParameter("period");
        String requestAnnualIncome = req.getParameter("annualIncome");
        String requestRegime=req.getParameter("regime");
        if (requestInvestment != null && requestReturn != null && requestPeriod != null) {
            double monthlyInvestment = Double.parseDouble(requestInvestment);
            double expectedReturn = Double.parseDouble(requestReturn);
            int totalPeriod = Integer.parseInt(requestPeriod);
            double monthlyInterestRate = expectedReturn / 12 / 100;
            double numOfMonths = 12 * totalPeriod;
            double totalReturn = monthlyInvestment * ((Math.pow((1 + monthlyInterestRate), (numOfMonths)) - 1) * (1 + monthlyInterestRate)) / monthlyInterestRate;
            double totalMoneyInvested = numOfMonths * monthlyInvestment;
            double EstimatedReturn = totalReturn - totalMoneyInvested;
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Estimated amount" + EstimatedReturn + "Total Amount" + totalMoneyInvested);
        }
        else{
            //calling function for tax old and new regime
            String received=findTax(Double.parseDouble(requestAnnualIncome),requestRegime);
            resp.getWriter().println(received);
        }
    }
        //tax slab
        public String findTax (Double annualIncome, String regime){
            double taxAmount = 0;
            switch (regime) {
                //for income slabs based on old tax slab
                case "old":
                    if (annualIncome <= 250000) {
                        System.out.println("No tax required to pay");
                    } else if (annualIncome > 250000 & annualIncome <= 500000) {
                        System.out.println("You have to pay an tax of 5%");
                        taxAmount = annualIncome * 0.05;
                        System.out.println("Tax amount =" + taxAmount);
                    } else if (annualIncome > 50000 & annualIncome <= 1000000) {
                        System.out.println("You have to pay an tax of 20%");
                        taxAmount = annualIncome * 0.2;
                        System.out.println("Tax amount =" + taxAmount);
                    } else {
                        System.out.println("You have to pay an tax of 30%");
                        taxAmount = annualIncome * 0.3;
                        System.out.println("Tax amount =" + taxAmount);
                    }
                    break;
                case "new":
                    //for income tax based on new tax slab
                    if (annualIncome <= 300000) {
                        System.out.println("No tax required to pay");
                    } else if (annualIncome >= 300001 & annualIncome <= 600000) {
                        System.out.println("You have to pay an tax of 5%");
                        taxAmount = annualIncome * 0.05;
                        System.out.println("Tax amount =" + taxAmount);
                    } else if (annualIncome >= 600001 & annualIncome <= 900000) {
                        System.out.println("You have to pay an tax of 10%");
                        taxAmount = annualIncome * 0.1;
                        System.out.println("Tax amount =" + taxAmount);
                    } else if (annualIncome >= 900001 & annualIncome <= 1200000) {
                        System.out.println("You have to pay an tax of 15%");
                        taxAmount = annualIncome * 0.15;
                        System.out.println("Tax amount =" + taxAmount);
                    } else if (annualIncome >= 1200001 & annualIncome <= 1500000) {
                        System.out.println("You have to pay an tax of 20%");
                        taxAmount = annualIncome * 0.2;
                        System.out.println("Tax amount =" + taxAmount);
                    } else {
                        System.out.println("You have to pay an tax of 30%");
                        taxAmount = annualIncome * 0.3;
                        System.out.println("Tax amount =" + taxAmount);
                    }
                    break;
            }
            return Double.toString(taxAmount);
        }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {

    }
}
