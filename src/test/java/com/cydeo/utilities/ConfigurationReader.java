package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
In this class, we will be creating the re-usable logic to read from
configuration.properties file
 */
public class ConfigurationReader {


    //1 - create the properties object (create object)
    //make it private, so it is not accessible from outside the class (limiting access) to object
    //static to make sure it is created and loaded before anything else and it belongs to the class
    private static Properties properties = new Properties();

    static { //cant type without a method/block
        try {
            //2 - open file using FileInputStream (open file)
            FileInputStream file = new FileInputStream("configuration.properties");
            //3 - load the "properties" object with file (load properties)
            properties.load(file);

            //we can close the file in the memory(after its loaded its not needed
            file.close();
        } catch (IOException e) {
            System.out.println("File not found with given path");
            e.printStackTrace();
        }
    }
    //now create a utility method to create an object to read
    //4 - use properties object to read from the file (read properties)

    public static String getProperty(String keyword) {
        return properties.getProperty(keyword);
    }


}
