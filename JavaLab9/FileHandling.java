package JavaLab9;

import java.io.*;

public class FileHandling {
    public static void main(String[] input)
    {
        String text = "This is the new file created with the intention of granting writable access.  \n" +
                "Once writable access is provided further functions takes place.";
       try{
           File file = new File("test.txt");
           file.setWritable(true);
           System.out.println("Access granted!!");
           FileWriter writer = new FileWriter(file);
           writer.write(text);
           System.out.println("Written successfully");
           writer.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file named test.txt");
            ex.printStackTrace();
        }
    }
}




