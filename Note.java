import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.*;

public class Note extends JPanel implements ActionListener {

    JLabel noteLabel = new JLabel("");
    JButton editNote = new JButton("Edit");
    JButton saveNote = new JButton("Save");
    JButton deleteNote = new JButton("Delete");
    JTextField noteField;
    ArrayList<String> linesList = NotesApp.linesList;

    Note(String noteWords) {
        setLayout(new FlowLayout()); // Use FlowLayout manager
        setPreferredSize(new Dimension(500, 20)); // Set preferred size for consistent height
        setBackground(Color.yellow);

        editNote.addActionListener(this);
        
        saveNote.addActionListener(this);
        deleteNote.addActionListener(this);
        editNote.setBackground(Color.CYAN);
        deleteNote.setBackground(Color.RED);
        saveNote.setBackground(Color.green);
        noteLabel.setPreferredSize(new Dimension(200, 20));

        noteLabel.setText(noteWords);

        add(noteLabel);
        add(editNote);
        add(deleteNote);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == editNote) {
            remove(noteLabel);
            remove(editNote);
            noteField = new JTextField(noteLabel.getText());
            noteField.setPreferredSize(new Dimension(200, 20));
            add(noteField, 0);
            add(saveNote, 1);
            revalidate();
        }

        if (e.getSource() == saveNote) {
            remove(saveNote);
            remove(noteField);
            repaint();

            String noteToReplace = noteLabel.getText();
            // System.out.println("Note to remove: "+noteToReplace);
            // for(String str:linesList){
            //     System.out.println(str);
            // }
            // System.out.println("------------");

            //replace in the arraylist
            int index = linesList.indexOf(noteToReplace);
            linesList.set(index, noteField.getText());

            try {
                FileWriter writer = new FileWriter("output.txt");
                for (String line : linesList) {
                    writer.write(line + "\n");
                    System.out.println(line);
                }
                writer.close();
            } catch (Exception err) {
                System.out.println(err);
            }      

            noteLabel.setText(noteField.getText());
            add(noteLabel, 0);
            add(editNote, 1);

            revalidate();
        }

        if (e.getSource() == deleteNote) {
            NotesApp frame = (NotesApp) SwingUtilities.getWindowAncestor(this);

            String noteToRemove = noteLabel.getText();
            linesList.removeIf(line -> line.equals(noteToRemove)); //remove from the list

            //sync the delete with the output.txt
            
            try {
                FileWriter writer = new FileWriter("output.txt");
                for (String line : linesList) {
                    writer.write(line + "\n");
                    System.out.println(line);
                }
                writer.close();
            } catch (Exception err) {
                System.out.println(err);
            }

            frame.remove(this);
            frame.revalidate();
            frame.repaint();
            revalidate();
            repaint();
        }

    }

}
