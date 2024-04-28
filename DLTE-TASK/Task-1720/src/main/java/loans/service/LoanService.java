package loans.service;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ManagedBean
@ViewScoped
public class LoanService {
    private List<Loans> loans;

    public List<Loans> getLoans() {
        return loans;
    }

    public void setLoans(List<Loans> loans) {
        this.loans = loans;
    }

    public LoanService () {
        loans = new ArrayList<>();
        loans.add(new Loans(101L,600000.0,"3/11/2024","active","Raksha",7876456787L));
        loans.add(new Loans(102L,56789.0,"05/04/2023","inactive","Rakshitha",7623456789L));
        loans.add(new Loans(103L,45670.0,"30/8/2025","active","Rashmitha",6789876789L));
    }

    //adding the new Loan
    public void addLoan(Loans newLoan) {
        try{
            loans.add(newLoan);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Added loans successfully",null));
        }catch (Exception e){
            System.out.println(e);
        }
    }
    //display closed loans
    public List<Loans> closedLoans() {
  return loans.stream().filter(each-> each.getLoanStatus().equalsIgnoreCase("inactive")).collect(Collectors.toList());

    }
    //delete the loans based on loan Number
    public void deleteLoans(long loanNumber){
        loans.removeIf(loan2 -> loan2.getLoanNumber()==loanNumber);
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Added loans successfully",null));
    }
}
