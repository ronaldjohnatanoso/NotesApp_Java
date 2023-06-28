import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.event.KeyEvent;


public class NewEntry extends JPanel implements ActionListener {
     ArrayList<String> linesList = NotesApp.linesList;
    JTextField writeField = new JTextField("");
    JButton addButton = new JButton("Add");
    JButton deleteButton = new JButton("Delete");
    JPanel top = new JPanel();

    public NewEntry() {
        setLayout(new FlowLayout()); // Use FlowLayout manager
        setPreferredSize(new Dimension(500, 20)); // Set preferred size for consistent height
        setBackground(Color.yellow);

        writeField.setPreferredSize(new Dimension(200, 20)); // Set preferred size for writeField
        writeField.setSize(200, 100);
        // Add key listener to the writeField
        writeField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addButton.doClick(); // Simulate click on addButton
                }
            }
        });

        addButton.addActionListener(this);
        addButton.setBackground(Color.GREEN);
        deleteButton.addActionListener(this);
        deleteButton.setBackground(Color.red);

        add(writeField);
        add(addButton);
        add(deleteButton);

        setVisible(true);



    }

@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == deleteButton) {
        writeField.setText("");
    }
    if (e.getSource() == addButton) {
        if (writeField.getText().trim().equals("")) {
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true)); // Append to the file
            writer.write(writeField.getText()+"\n");
            writer.close();
            linesList.add(writeField.getText());
            // System.out.println("Data written to the file: "+writeField.getText()+" ,at index: "+linesList.indexOf(writeField.getText()));
        } catch (Exception err) {
            System.out.println(err);
        }

        NotesApp frame = (NotesApp) SwingUtilities.getWindowAncestor(this);
        frame.add(new Note(writeField.getText()));
        frame.revalidate();
        writeField.setText("");
    }
}
}
