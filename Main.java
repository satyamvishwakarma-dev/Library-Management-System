import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Commands cmd = new Commands();
        String user, pass;      //string var
        int choice;             //int var

        Scanner sc = new Scanner(System.in);    //User Input

        //while
        while (true) {
            //login
            System.out.print("User Name: ");
            user = sc.nextLine();
            System.out.print("Password: ");
            pass = sc.nextLine();
            if (cmd.login(user, pass)) {
                System.out.println("Welcome, Admin");
                System.out.println("""
                        Choose from the following:
                        1 -> Add Book to library
                        2 -> Check Book
                        3 -> Issue Book
                        4 -> Return Book
                        5 -> Exit""");
                //choices
                System.out.print("-> ");
                choice = sc.nextInt();

                //choice 1
                if (choice==1){
                    break;
                }//end choice 1

                //choice to end
                else if (choice==5){
                    System.out.println("Thank You for using our service\n" +
                            "Have a Great Day!!");
                    return;
                }
            } else {
                System.out.println("INVALID USERNAME/PASSWORD");
            }//end login if-else
        }//end while
    }//end main
}//end class
