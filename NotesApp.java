import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;



class NotesApp extends JFrame {
    JLabel appLabel = new JLabel("NotesApp");
    public static ArrayList<String> linesList = new ArrayList<String>();
    public NotesApp(){
        setBounds(500, 100 ,500, 500);
        setLayout(new GridLayout(10,1));
        setTitle("NotesApp");

        appLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label horizontally
        appLabel.setPreferredSize(new Dimension(150, 50)); // Set preferred size
        Font font = new Font("Arial", Font.BOLD, 24); // Create a Font object with desired font settings
        appLabel.setFont(font);
        getContentPane().setBackground(Color.yellow);

        add(appLabel);
        add(new NewEntry());

        //load already written data from output.txt
        try {
            BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                // Process each line of the file here

                linesList.add(line);
                add(new Note(line));
            } 
            reader.close();
        } catch (Exception err) {
            System.out.println(err);
        }

        setVisible(true);
    }

    public static void main(String[] args){
        new NotesApp();
        //hey
    }

}