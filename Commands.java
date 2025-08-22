import java.io.*;

public class Commands {
    //login command
    public static boolean login(String username, String password) {
        if (username.equals("admin")) {
            return password.equals("1234");
        }//end if
        return false;
    }//end login()

    //login thing
    public String toCSV(int bookId,String name, String author, String edition, int qty) {
        StringBuilder sb = new StringBuilder();
        sb.append(bookId).append(",").append(name).append(",").append(author).append(",").append(edition).append(",").append(qty);
        return sb.toString();
    }//end toCSV()

    // Method to issue a book (delete its row from CSV)
    public static void issueBook(String bookIdToDelete) {
        String filePath = "database.csv";   // File path fixed inside method
        String tempFile = "temp.csv";    // Temporary file

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
    }//end issueBook()

}//end class Command
