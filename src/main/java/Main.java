
import com.importio.util.TimerRead;
import com.importio.util.TimerWrite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Timer;

public class Main extends JFrame{
    public static void main(String[] args) {
        System.out.println("Start program");

       Frame frame = new Frame();
       frame.createGUI();
    }
}
class Frame extends JFrame{
    private JTextField textField;

    public void createGUI(){
        TimerRead timerRead  = new TimerRead();
        TimerWrite timerWrite= new TimerWrite();
        Timer timer = new Timer();

        int timeDelay = 10000;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        textField = new JTextField();
        textField.setColumns(23);


        JButton button1 = new JButton("Start Read");
        button1.setActionCommand("Start Read");
        panel.add(button1);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.schedule(timerRead, (long) timeDelay, (long) timeDelay);
                textField.setText("Start Read");
            }
        });

        JButton button2 = new JButton("Stop Read");
        button2.setActionCommand("Stop Read");
        panel.add(button2);

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerRead.cancel();
                textField.setText("Stop Read");
            }
        });

        JButton button3 = new JButton("Start Write");
        button3.setActionCommand("Start Write");
        panel.add(button3);

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.schedule(timerWrite, (long) timeDelay, (long) timeDelay);
                textField.setText("Start Write");
            }
        });

        JButton button4 = new JButton("Stop Write");
        button4.setActionCommand("Stop Write");
        panel.add(button4);

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerWrite.cancel();
                textField.setText("Stop Write");
            }
        });

        panel.add(textField);

        setSize(500,500);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(320, 100));
        setVisible(true);
    }


}