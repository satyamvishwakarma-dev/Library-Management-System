import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class Commands {
    //login command
    public static boolean login(String username, String password) {
        if (username.equals("admin")) {
            return password.equals("1234");
        }//end if
        return false;
    }//end login()

    // Method to issue a book (delete its row from CSV)
    public static void deleteFromDatabase(int bookIdToDelete) {
        String filePath = "database.csv";

        try {
            var lines = Files.lines(Paths.get(filePath)).collect(Collectors.toList());

            // First line is header, keep it always
            String header = lines.get(0);

            // Filter out matching bookID (skip header)
            var updatedLines = lines.stream()
                    .skip(1) // skip header for filtering
                    .filter(line -> {
                        String[] data = line.split(",");
                        int currentId = Integer.parseInt(data[0].trim());
                        return currentId != bookIdToDelete; // keep all except this ID
                    })
                    .collect(Collectors.toList());

            // Add back header on top
            updatedLines.add(0, header);

            // Write updated lines back to file
            Files.write(Paths.get(filePath), updatedLines);

            System.out.println("Book with ID " + bookIdToDelete + " deleted successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end DeleteFromDatabase()

    // Method to add a new book to CSV
    public static void addToDatabase(int bookId, String title, String author, String edition) {
        String filePath = "database.csv";  // Fixed CSV file path

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            // Format: BookID,Title,Author,Edition
            String newBook = bookId + "," + title + "," + author + "," + edition;

            bw.write(newBook);
            bw.newLine();  // Move to next line
            bw.flush();

            System.out.println("\nBook details added successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end addToDatabase

    public static void deleteFromIssueDatabase(int bookIdToDelete) {
        String filePath = "issuedBook.csv";   // File path fixed inside method
        String tempFile = "temp1.csv";    // Temporary file

        File oldFile = new File(filePath);
        File newFile = new File(tempFile);

        try (
                BufferedReader br = new BufferedReader(new FileReader(oldFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(newFile))
        ) {
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");  // CSV split
                if (data[0].equals(bookIdToDelete)) {
                    // Skip this row -> Book issued (deleted from file)
                    found = true;
                    continue;
                }
                bw.write(line);
                bw.newLine();
            }

            bw.flush();

            // Replace old file with updated temp file
            if (oldFile.delete()) {
                newFile.renameTo(oldFile);
            }

            if (found) {
                System.out.println("Book with ID " + bookIdToDelete + " issued successfully!");
            } else {
                System.out.println("Book ID " + bookIdToDelete + " not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end DeleteFromIssueDatabase()

    // Method to add a new book to CSV
    public static void AddToReturnedDatabase(int bookId, String title, String author, String edition, String returnedDate) {
        String filePath = "returnedBook.csv";  // Fixed CSV file path

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            // Format: BookID,Title,Author,Edition
            String newBook = bookId + "," + title + "," + author + "," + edition + "," + returnedDate;

            bw.write(newBook);
            bw.newLine();  // Move to next line
            bw.flush();

            System.out.println("\nBook details added successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end addToDatabase

    // Method to add a new book to CSV
    public static void AddToIssueDatabase(int bookId, String title, String author, String edition, String returnDate) {
        String filePath = "issuedBook.csv";  // Fixed CSV file path

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            // Format: BookID,Title,Author,Edition
            String newBook = bookId + "," + title + "," + author + "," + edition + "," + returnDate + "\n";

            bw.write(newBook);
            bw.newLine();  // Move to next line
            bw.flush();

            System.out.println("\nBook details added successfully");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end addToDatabase

}//end class Command
