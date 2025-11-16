package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener
{
    String pin;
    JButton b1,b2,b3,b4,b5,b6, back;
    FastCash(String pin)
    {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550,830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("SELECT WITHDRAWAL AMOUNT");
        label.setBounds(445,180,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,23));
        l3.add(label);

        b1 = new JButton("Rs. 100");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65,125,128));
        b1.setBounds(410,274,150,35);
        b1.setFont(new Font("System",Font.BOLD,16));
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Rs. 500");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65,125,128));
        b2.setBounds(700,274,150,35);
        b2.setFont(new Font("System",Font.BOLD,16));
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("Rs. 1000");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65,125,128));
        b3.setBounds(410,318,150,35);
        b3.setFont(new Font("System",Font.BOLD,16));
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65,125,128));
        b4.setBounds(700,318,150,35);
        b4.setFont(new Font("System",Font.BOLD,16));
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("Rs. 5000");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65,125,128));
        b5.setBounds(410,362,150,35);
        b5.setFont(new Font("System",Font.BOLD,16));
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("Rs. 10000");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65,125,128));
        b6.setBounds(700,362,150,35);
        b6.setFont(new Font("System",Font.BOLD,16));
        b6.addActionListener(this);
        l3.add(b6);

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,128));
        back.setBounds(700,406,150,35);
        back.setFont(new Font("System",Font.BOLD,16));
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
        if(e.getSource()== back)
        {
            setVisible(false);
            new MainClass(pin);
        }
        else
        {
            String amount = ((JButton)e.getSource()).getText().substring(4);
            Jdbc con = new Jdbc();
            Date date = new Date();
            try
            {
                ResultSet resultSet = con.statement.executeQuery("select * from bank where pin = "+pin+"");
                int balance = 0;
                while (resultSet.next())
                {
                    if(resultSet.getString("type").equals("Deposit"))
                    {
                        balance += Integer.parseInt(resultSet.getString("ammount"));
                    }
                    else
                    {
                        balance -= Integer.parseInt(resultSet.getString("ammount"));
                    }
                }
                String num = "17";

                if(e.getSource() != back && balance < Integer.parseInt(amount))
                {
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }
                con.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdraw', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. " +amount+ " Debited Successfully");
            }
            catch (Exception E)
            {
                E.printStackTrace();
            }
            setVisible(false);
            new MainClass(pin);
        }
    }

    public static void main(String[] args)
    {
        new FastCash("");
    }
}
