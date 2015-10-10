package primefinder;

/* date: Oct 8, 2015
 *
 * author: Heather Khoury <hrkhoury314@gmail.com>
 *
 * project: GUI app to list prime numbers 
 *          Based off Hour 19 - Threads from book "Java in 24 Hours"
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PrimeFinder extends JFrame implements Runnable, ActionListener {
    Thread go;
    JLabel howManyLabel;
    JTextField howMany;
    JButton display;
    JTextArea primes;
    
    public PrimeFinder() {
        super("Find Prime Numbers");
        
        setLookAndFeel();
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout bord = new BorderLayout();
        setLayout(bord);
        
        howManyLabel = new JLabel("Quantity: ");
        howMany = new JTextField("400", 10);
        display = new JButton("Display Primes");
        primes = new JTextArea(8, 40);
        
        display.addActionListener(this);
        JPanel topPanel = new JPanel();
        topPanel.add(howManyLabel);
        topPanel.add(howMany);
        topPanel.add(display);
        add(topPanel, BorderLayout.NORTH);
        
        primes.setLineWrap(true);
        JScrollPane textPane = new JScrollPane(primes);
        add(textPane, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == display) {
           display.setEnabled(false);
           primes.setText(null);
           if ( go == null) {
                go = new Thread(this);
                go.start();
           }
	}
    }
    
    
    public void run() {
        int quantity = Integer.parseInt(howMany.getText());
        int numPrimes = 0;
        //candidate: the number that might be prime
        int candidate = 2;
        primes.append("First " + quantity + " primes: ");
        while (numPrimes < quantity) {
            if (isPrime(candidate)) {
                primes.append(candidate + " ");
                numPrimes++;
            }
            candidate++;
        }
    }        
   
    
    public static boolean isPrime(int checkNumber) {
        double root = Math.sqrt(checkNumber);
        for (int i = 2; i <= root; i++) {
            if (checkNumber % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception e) {
            //ignore exception
        }
    }
    
    public static void main(String[] args) {
        new PrimeFinder();
    }
}
