package basics.service;
import java.util.*;
public class Insurance {
    public static void main(String args[]){
        String feature="";
        String LiabilityInsurance[]={"accident cover","theft cover","cashless claim settlement"};
        String AckoCarInsurance[]={"own damage insurance","accident cover","Instant support"};
        String TwoWheelerInsurance[]={"accident cover","roadside assistence","third party liability"};
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the feature");
        feature=scanner.nextLine();
        String companyOffer="";
        for(int index=0;index<LiabilityInsurance.length;index++){
            if(feature.toLowerCase().contains(LiabilityInsurance[index]))
                companyOffer+="LiabilityInsurance";
        }
        for(int index=0;index<AckoCarInsurance.length;index++){
            if(feature.toLowerCase().contains(AckoCarInsurance[index]))
                companyOffer+="AckoCarInsurance";
        }
        for(int index=0;index<TwoWheelerInsurance.length;index++){
            if(feature.toLowerCase().contains(TwoWheelerInsurance[index]))
                companyOffer+="TwoWheelerInsurance";
        }
        System.out.println("Companies that offer the feature are"+companyOffer);
    }
}
