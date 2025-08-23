import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);    //User Input
        String user, code, title, auther, edition, addAnother = "",issuetitle;
        String issueauther,issueedition,issueDate,returnedDate,returnedtitle,returnedauther,returnededition;      //string var
        int choice, bookId,issuebookId,returnedbookId;             //int var
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
                    ---
                    Choose from the following:
                    1 -> Add Book to library
                    2 -> Check Books
                    3 -> Issue Book
                    4 -> Return Book
                    5 -> Issued Books Record
                    6 -> Returned Books Record
                    7 -> Exit""");
            //choices
            System.out.print(">>> ");
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

                    Commands.addToDatabase(bookId,title,auther,edition);

                    if (!addAnother.equals("yes")) {
                        break;
                    }//end if
                } while (true); //inner do-while choice 1
            }//end write book

            //choice 2 |view database
            else if (choice == 2) {
                System.out.println("\nDetails of Books currently in Library: ");
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
                System.out.print("Enter the book ID: ");
                issuebookId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter the Title of the book: ");
                issuetitle = sc.nextLine();

                System.out.print("Enter the Author of the book: ");
                issueauther = sc.nextLine();

                System.out.print("Enter the Edition of the book: ");
                issueedition = sc.nextLine();

                System.out.print("Enter the date of issuing(DD/MM/YYYY): ");
                issueDate = sc.nextLine();

                Commands.deleteFromDatabase(issuebookId);

                Commands.AddToIssueDatabase(issuebookId,issuetitle,issueauther,issueedition,issueDate);
            }//end choice 3

            //choice 4 | Return book
            else if (choice == 4) {
                System.out.print("Enter the book ID: ");
                returnedbookId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter the Title of the book: ");
                returnedtitle = sc.nextLine();

                System.out.print("Enter the Author of the book: ");
                returnedauther = sc.nextLine();

                System.out.print("Enter the Edition of the book: ");
                returnededition = sc.nextLine();

                System.out.print("Enter the date of issuing(DD/MM/YYYY): ");
                returnedDate = sc.nextLine();

                Commands.AddToReturnedDatabase(returnedbookId,returnedtitle,returnedauther,returnededition,returnedDate);

                Commands.addToDatabase(returnedbookId,returnedtitle,returnedauther,returnededition);
            }//end choice 4

            //choice 5 /Issued books
            else if (choice == 5) {
                System.out.println("\nDetails of Books issued to Library: ");
                try (BufferedReader br = new BufferedReader(new FileReader("issuedBook.csv"))) {
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
            }//end choice 5

            //choice 6 / Returned books
            else if (choice == 6) {
                System.out.println("\nDetails of Books returned to Library: ");
                try (BufferedReader br = new BufferedReader(new FileReader("returnedBook.csv"))) {
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
            }//end choice 6

            //choice 7 | End
            else if (choice == 7) {
                System.out.println("Thank You for using our service\n" +
                        "Have a Great Day!!");
                return;
            }//end choice 7
        }//end while
    }//end main
}//end class
