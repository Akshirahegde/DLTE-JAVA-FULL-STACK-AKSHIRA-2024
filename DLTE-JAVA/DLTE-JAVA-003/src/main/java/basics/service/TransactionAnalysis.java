package basics.service;
import java.util.Date;
import java.util.Scanner;

public class TransactionAnalysis {
    public static void main(String args[]){
        Transaction[] myBank={
                new Transaction(new Date(2024,03,3),100,"Avisha","Family"),
                new Transaction(new Date(2023,05,9),10700,"Avi","Emergency"),
                new Transaction(new Date(2023,06,3),20000,"Amir","Family"),
                new Transaction(new Date(2024,07,8),1900,"sam","Edcation"),
                new Transaction(new Date(2019,03,16),8000,"ram","Bills"),

        };
        Scanner scanner = new Scanner(System.in);
            System.out.println("Choose from the choices below");
            System.out.println("1.Filter based on given ranges of date\n");
            System.out.println("2.Find the least amount transferred\n");
            System.out.println("3.Find the maximum amount transferred\n");
            System.out.println(" 4.Find the number of transaction made to particular beneficiary");
            System.out.println("5.filter based on particular remarks");
            System.out.println("6.Sort based on Beneficiary in descending");
            System.out.println("7.Sort based on amount in ascending");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
            }

    }
}
