package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class FastCash extends JFrame implements ActionListener
{
    String pin;
    JButton b1, b2, b3, b4, b5, b6, back;

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
        label.setFont(new Font("System", Font.BOLD, 23));
        l3.add(label);

        b1 = new JButton("Rs. 100");
        b2 = new JButton("Rs. 500");
        b3 = new JButton("Rs. 1000");
        b4 = new JButton("Rs. 2000");
        b5 = new JButton("Rs. 5000");
        b6 = new JButton("Rs. 10000");

        JButton[] buttons = {b1, b2, b3, b4, b5, b6};
        int x1 = 410, x2 = 700;
        int y = 274;

        for (int i = 0; i < buttons.length; i++)
        {
            JButton btn = buttons[i];
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(65,125,128));
            btn.setFont(new Font("System", Font.BOLD, 16));
            btn.addActionListener(this);

            if (i % 2 == 0)
                btn.setBounds(x1, y, 150, 35);
            else
                btn.setBounds(x2, y, 150, 35);

            if (i % 2 == 1) y += 44;

            l3.add(btn);
        }

        back = new JButton("BACK");
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,128));
        back.setBounds(700,406,150,35);
        back.setFont(new Font("System", Font.BOLD, 16));
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
        if (e.getSource() == back)
        {
            setVisible(false);
            new MainClass(pin);
            return;
        }

        // FastCash button amount extract
        String amountText = ((JButton)e.getSource()).getText().substring(4);
        int amt = Integer.parseInt(amountText);

        try
        {
            Jdbc con = new Jdbc();

            // ================================
            // STEP 1: CHECK TODAY'S WITHDRAWAL
            // ================================
            String today = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

            String q1 = "SELECT SUM(ammount) AS total FROM bank "
                    + "WHERE pin='" + pin + "' "
                    + "AND type='Withdrawal' "
                    + "AND `date` LIKE '" + today + "%'";

            ResultSet rs1 = con.statement.executeQuery(q1);
            int todayTotal = 0;
            if (rs1.next()) todayTotal = rs1.getInt("total");

            if (todayTotal + amt > 20000)
            {
                JOptionPane.showMessageDialog(null,
                        "Daily limit exceeded!\n" +
                                "You already withdrew ₹" + todayTotal + " today.\n" +
                                "Remaining limit: ₹" + Math.max(0, (20000 - todayTotal))
                );
                return;
            }

            // ================================
            // STEP 2: CHECK BALANCE
            // ================================
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

            // ================================
            // STEP 3: INSERT WITHDRAWAL RECORD
            // ================================
            String datetime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

            String q2 = "INSERT INTO bank VALUES('" + pin + "', '" + datetime + "', 'Withdrawal', '" + amt + "')";
            con.statement.executeUpdate(q2);

            JOptionPane.showMessageDialog(null, "Rs. " + amt + " Debited Successfully");

            setVisible(false);
            new MainClass(pin);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong!");
        }
    }


    public static void main(String[] args)
    {
        new FastCash("");
    }
}
