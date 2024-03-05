package basics.service;
import java.util.*;
public class Insurance {
    public static void main(String args[]){
        String feature="";
        String LiabilityInsurance[]={"accidentcover","theft cover","cashless claim settlement"};
        String AckoCarInsurance[]={"own damage insurance","accidentcover","Instantsupport"};
        String TwoWheelerInsurance[]={"accident cover","roadside assistence","third party liability"};
        Scanner scanner=new Scanner(System.in);
//        System.out.println("Enter the feature");
//        feature=scanner.nextLine();
//        String companyOffer="";
      //  for(int index=0;index<LiabilityInsurance.length;index++)
        for(String each:args) {
            for (String select : LiabilityInsurance) {

                    if(each.equalsIgnoreCase(select)){
                        System.out.println("Liability Insurance"+select);
                    }
            }
        }
        for(String each:args) {
            for (String select : AckoCarInsurance) {

                if(each.equalsIgnoreCase(select)){
                    System.out.println("Acko Car Insurance"+select);
                }
            }
        }
        for(String each:args) {
            for (String select : TwoWheelerInsurance) {

                if(each.equalsIgnoreCase(select)){
                    System.out.println("Two wheeler Insurance"+select);
                }
            }
        }
//        System.out.println("Companies that offer the feature are"+companyOffer);
    }
}
