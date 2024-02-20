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
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
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
                    transactionAnalysis.filterbyDateRange(myBank);
                    break;
                case 2:
                    transactionAnalysis.leastAmountTransferred(myBank);
                    break;
                case 3:
                    transactionAnalysis.maximumAmountTransferred(myBank);
                    break;
                case 4:
                    transactionAnalysis.transactionToBenificiary(myBank);
                    break;
                case 5:
                    transactionAnalysis.filterbyRemarks(myBank);
                    break;
                case 6:
                    transactionAnalysis.sortBenificiary(myBank);
                    break;
                case 7:
                    transactionAnalysis.sortAmount(myBank);
                    break;

            }

    }
    //    Filter based on given ranges of date
    public void filterbyDateRange(Transaction[] transaction){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the start the range of the data(yyyy/mm/dd)");
         String StartDate=scanner.next();
         String splitStartDate[]=StartDate.split("/");
         for (Transaction each:transaction){
             if(Integer.parseInt(splitStartDate[0])==each.getDateOfTransaction().getYear() && Integer.parseInt(splitStartDate[1])==each.getDateOfTransaction().getMonth() && Integer.parseInt(splitStartDate[2])==each.getDateOfTransaction().getDate()){
                 System.out.println("Transaction on date"+each.getDateOfTransaction().getDate()+"to"+each.getTransactionReciever());
             }
         }

    }
    //    least amount transferred
    public void leastAmountTransferred(Transaction[] transaction){
        Transaction minTransaction=transaction[0];
        for(int index=1;index<transaction.length;index++) {
            if (transaction[index].getAmountInTransaction() < minTransaction.getAmountInTransaction()) {
                minTransaction = transaction[index];
            }
        }
            System.out.println("The least amount transferred is"+minTransaction.getAmountInTransaction());


    }
    //    maximum amount transferred
    public void maximumAmountTransferred(Transaction[] transaction){
        Transaction maxTransaction=transaction[0];
        for(int index=1;index<transaction.length;index++) {
            if (transaction[index].getAmountInTransaction() > maxTransaction.getAmountInTransaction()) {
                maxTransaction = transaction[index];
            }
        }
            System.out.println("The maximum amount transferred is"+maxTransaction.getAmountInTransaction());


    }
    //    number of transaction made to particular beneficiary
    public void transactionToBenificiary(Transaction[] transaction){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the benificiary name:");
        String benificiaryName=scanner.nextLine();
        int count=0;
        for (int index=0;index<transaction.length;index++){
            if (transaction[index].getTransactionReciever().equalsIgnoreCase(benificiaryName)){
                count++;
            }
        }
        System.out.println("The no of transaction made to the "+benificiaryName+"is"+count);

    }
    //    filter based on particular remarks
    public void filterbyRemarks(Transaction[] transaction) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the remark");
        String remark = scanner.nextLine();
        for (int index = 0; index < transaction.length; index++) {
            if (transaction[index].getRemarks().equalsIgnoreCase(remark)) {
                System.out.println("the transaction with remark "+ remark+"is made to"+transaction[index].getTransactionReciever()+"on"+transaction[index].getDateOfTransaction());

            }
            }
    }
    //    based on Beneficiary in descending
    public void sortBenificiary(Transaction[] transaction){
        Transaction backup=null;
        for (int select=0;select<transaction.length-1;select++) {
            for (int next = 0; next < transaction.length - select - 1; next++) {
                if (transaction[select].getTransactionReciever().compareTo(transaction[next].getTransactionReciever()) < 0) {
                    backup = transaction[select];
                    transaction[select] = transaction[next];
                    transaction[next] = backup;
                }
            }

        }
        for (Transaction each : transaction) {
            System.out.println(each.getTransactionReciever());
        }
    }
 //   based on amount in ascending
    public void sortAmount(Transaction[] transaction){
        Transaction backup=null;
        for (int select=0;select<transaction.length-1;select++){
            for(int next=0;next<transaction.length-select-1;next++){
                if (transaction[select].getAmountInTransaction() > (transaction[next].getAmountInTransaction())){
                    backup=transaction[select];
                    transaction[select]=transaction[next];
                    transaction[next]=backup;
                }
            }

        }
        for (Transaction each : transaction) {
            System.out.println(each.getAmountInTransaction());
        }
    }
}
