package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass extends JFrame implements ActionListener
{
    String pin;
    JButton deposit, withdrawal, fast_cash, mini_statement, pin_change,balance_enquiry, exit;
    MainClass(String pin)
    {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("Please select your Transaction");
        label.setBounds(430,180,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,28));
        l3.add(label);

        deposit = new JButton("DEPOSIT");
        deposit.setForeground(Color.WHITE);
        deposit.setBackground(new Color(65,125,128));
        deposit.setBounds(410,274,150,35);
        deposit.addActionListener(this);
        l3.add(deposit);

        withdrawal = new JButton("WITHDRAWAL");
        withdrawal.setForeground(Color.WHITE);
        withdrawal.setBackground(new Color(65,125,128));
        withdrawal.setBounds(700,274,150,35);
        withdrawal.addActionListener(this);
        l3.add(withdrawal);

        fast_cash = new JButton("FAST CASH");
        fast_cash.setForeground(Color.WHITE);
        fast_cash.setBackground(new Color(65,125,128));
        fast_cash.setBounds(410,318,150,35);
        fast_cash.addActionListener(this);
        l3.add(fast_cash);

        mini_statement = new JButton("MINI STATEMENT");
        mini_statement.setForeground(Color.WHITE);
        mini_statement.setBackground(new Color(65,125,128));
        mini_statement.setBounds(700,318,150,35);
        mini_statement.addActionListener(this);
        l3.add(mini_statement);

        pin_change = new JButton("PIN CHANGE");
        pin_change.setForeground(Color.WHITE);
        pin_change.setBackground(new Color(65,125,128));
        pin_change.setBounds(410,362,150,35);
        pin_change.addActionListener(this);
        l3.add(pin_change);

        balance_enquiry = new JButton("BALANCE ENQUIRY");
        balance_enquiry.setForeground(Color.WHITE);
        balance_enquiry.setBackground(new Color(65,125,128));
        balance_enquiry.setBounds(700,362,150,35);
        balance_enquiry.addActionListener(this);
        l3.add(balance_enquiry);

        exit = new JButton("EXIT");
        exit.setForeground(Color.WHITE);
        exit.setBackground(new Color(65,125,128));
        exit.setBounds(700,406,150,35);
        exit.addActionListener(this);
        l3.add(exit);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==deposit)
        {
            new Deposit(pin);
            setVisible(false);
        } else if (e.getSource()==exit)
        {
            System.exit(0);
        } else if (e.getSource()==withdrawal)
        {
            new Withdrawal(pin);
            setVisible(false);
        } else if (e.getSource()==balance_enquiry)
        {
            new BalanceEnquiry(pin);
            setVisible(false);
        } else if (e.getSource()==fast_cash)
        {
            new FastCash(pin);
            setVisible(false);
        } else if (e.getSource()==pin_change)
        {
            new PinChange(pin);
            setVisible(false);
        } else if (e.getSource()==mini_statement)
        {
            new MiniStatement(pin);
        }
    }

    public static void main(String[] args)
    {
        new MainClass("");
    }
}
