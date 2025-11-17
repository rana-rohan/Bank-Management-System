package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

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

        JLabel label1 = new JLabel("DAILY WITHDRAWAL LIMIT: 20,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 700, 35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460, 220, 400, 35);
        l3.add(label2);

        textField = new TextField();
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460,260,320,25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        withdrawal = new JButton("WITHDRAW");
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
        if (e.getSource() == withdrawal)
        {
            String amount = textField.getText().trim();

            if (amount.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                return;
            }

            try
            {
                int amt = Integer.parseInt(amount);
                if (amt <= 0)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a positive amount!");
                    return;
                }
                if (amt > 20000)
                {
                    JOptionPane.showMessageDialog(null, "You cannot withdraw more than ₹20,000 at once!");
                    return;
                }
                Jdbc con = new Jdbc();
                String todayPrefix = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
                String q1 = "SELECT SUM(ammount) AS total FROM bank " + "WHERE pin='" + pin + "' " + "AND type='Withdrawal' " + "AND `date` LIKE '" + todayPrefix + "%'";
                ResultSet rs1 = con.statement.executeQuery(q1);
                int todayTotal = 0;
                if (rs1.next())
                {
                    todayTotal = rs1.getInt("total");
                }
                if (todayTotal + amt > 20000)
                {
                    JOptionPane.showMessageDialog(null, "Daily limit exceeded!\n" + "You already withdrew ₹" + todayTotal + " today.\n" + "Remaining limit: ₹" + (20000 - todayTotal));
                    return;
                }
                ResultSet rs = con.statement.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");
                int balance = 0;
                while (rs.next())
                {
                    String type = rs.getString("type");
                    int val = Integer.parseInt(rs.getString("ammount"));
                    if (type.equals("Deposit")) balance += val;
                    else if (type.equals("Withdrawal")) balance -= val;
                }
                if (balance < amt)
                {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance!");
                    return;
                }
                String datetime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                String insertQuery = "INSERT INTO bank VALUES('" + pin + "', '" + datetime + "', 'Withdrawal', '" + amt + "')";
                Jdbc con2 = new Jdbc();
                con2.statement.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(null, "Rs. " + amt + " debited successfully");
                setVisible(false);
                new MainClass(pin);
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount!");
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "SQL ERROR: " + ex.getMessage());
            }
        }
        else if (e.getSource() == back)
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
