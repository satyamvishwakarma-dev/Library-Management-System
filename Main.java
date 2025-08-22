import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Commands cmd = new Commands();
        Scanner sc = new Scanner(System.in);    //User Input
        String user, code, title, auther, edition, addAnother;      //string var
        int choice, qty, bookId,issue_id;             //int var
        boolean access;

        //login:
        do {
            System.out.print("Username: ");
            user = sc.nextLine();
            System.out.print("Password: ");
            code = sc.nextLine();
            access = Commands.login(user, code);

            if (!access) {
                System.out.println(" ");
                System.out.println("ACCESS DENIED..\nPLEASE TRY AGAIN!!\n\n");
                System.out.println(" ");
            }
        } while (!access);
        System.out.println("Login successful.\nWelcome!!\n\n");
        //end login

        //while
        while (true) {
            System.out.println("""
                    Choose from the following:
                    1 -> Add Book to library
                    2 -> Check Books
                    3 -> Issue Book
                    4 -> Return Book
                    5 -> Exit""");
            //choices
            System.out.print(">>>");
            choice = sc.nextInt();
            sc.nextLine();

            //choice 1 | Enter the book
            if (choice == 1) {
                do {
                    System.out.print("Enter the book ID: ");
                    bookId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter the Title of the book: ");
                    title = sc.nextLine();

                    System.out.print("Enter the Author of the book: ");
                    auther = sc.nextLine();

                    System.out.print("Enter the Edition of the book: ");
                    edition = sc.nextLine();

                    System.out.print("Enter the Quantity of the book: ");
                    qty = sc.nextInt();
                    sc.nextLine(); // Clear the input buffer

                    // ✅ Save data to CSV file
                    try (FileWriter fw = new FileWriter("database.csv", true)) {
                        fw.write(cmd.toCSV(bookId, title, auther, edition, qty) + "\n");
                    } catch (IOException e) {
                        System.out.println("❌ Error saving book data: " + e.getMessage());
                    }

                    System.out.print("Want to add another book? (yes/no): ");
                    addAnother = sc.nextLine().toLowerCase();

                    if (!addAnother.equals("yes")) {
                        break;
                    }//end if
                } while (true); //inner do-while choice 1
            }//end write book

            //choice 2 |view data
            else if (choice == 2) {
                try (BufferedReader br = new BufferedReader(new FileReader("database.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        for (String cell : data) {
                            System.out.print(cell + "\t");
                        }
                        System.out.println();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }//end choice 2

            //choice 3 |issue book
            else if (choice == 3) {

            }//end choice 3

            //choice 4 | Return book
            else if (choice == 4) {

            }//end choice 4

            //choice 5 | End
            else if (choice == 5) {
                System.out.println("Thank You for using our service\n" +
                        "Have a Great Day!!");
                return;
            }
        }//end while
    }//end main
}//end class
