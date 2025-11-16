package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener
{
    String pin;
    TextField textField;
    JButton withdrawal, back;
    Withdrawal(String pin)
    {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS 10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System",Font.BOLD,16));
        label1.setBounds(460,180,700,35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System",Font.BOLD,16));
        label2.setBounds(460,220,400,35);
        l3.add(label2);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,320,25);
        textField.setFont(new Font("Rale way",Font.BOLD,22));
        l3.add(textField);

        withdrawal = new JButton("WITHDRAWAL");
        withdrawal.setBounds(700,362,150,35);
        withdrawal.setBackground(new Color(65,125,128));
        withdrawal.setForeground(Color.WHITE);
        withdrawal.addActionListener(this);
        l3.add(withdrawal);

        back = new JButton("BACK");
        back.setBounds(700,406,150,35);
        back.setBackground(new Color(65,125,128));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        l3.add(back);











        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==withdrawal)
        {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (textField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                } else {
                    Jdbc con5 = new Jdbc();
                    ResultSet resultSet = con5.statement.executeQuery("select * from bank where pin = " + pin + "");
                    int balance = 0;
                    while (resultSet.next())
                    {
                        if (resultSet.getString("type").equals("Deposit"))
                        {
                            balance += Integer.parseInt(resultSet.getString("ammount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("ammount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount))
                    {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }
                    con5.statement.executeUpdate("insert into bank values('" + pin + "', '" + date + "', 'Withdrawal', '" + amount + "')");
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
                    setVisible(false);
                    new MainClass(pin);
                }
            } catch (Exception E)
            {

            }
        }
        else if (e.getSource()==back)
        {
            setVisible(false);
            new MainClass(pin);
        }
    }

    public static void main(String[] args)
    {
        new Withdrawal("");
    }
}