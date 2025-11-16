package bank.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Signup extends JFrame implements ActionListener
{
    JRadioButton male, female, married, unmarried, other;
    JButton next;
    JTextField text_name, text_father_name, text_email, text_address, text_city, text_pin, text_state;
    JDateChooser dateChooser;
    Random ran = new Random();
    long first4 = (ran.nextLong() % 9000L) + 1000L;
    String first = " " + Math.abs(first4);
    Signup()
    {
        super("APPLICATION FORM");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25,10,100,100);
        add(image);

        JLabel label1 = new JLabel("APPLICATION FORM NO. " + first);
        label1.setBounds(130,20,600,40);
        label1.setFont(new Font("Raleway",Font.BOLD,38));
        add(label1);

        JLabel label2 = new JLabel("Page 1");
        label2.setFont(new Font("Raleway",Font.BOLD,22));
        label2.setBounds(330,70,600,30);
        add(label2);

        JLabel label3 = new JLabel("Personal Details");
        label3.setFont(new Font("Raleway",Font.BOLD,22));
        label3.setBounds(290,90,600,30);
        add(label3);

        JLabel labelName = new JLabel("Name :");
        labelName.setFont(new Font("Raleway",Font.BOLD,20));
        labelName.setBounds(100,190,100,30);
        add(labelName);

        text_name = new JTextField();
        text_name.setFont(new Font("Raleway",Font.BOLD,14));
        text_name.setBounds(300,190,400,30);
        add(text_name);

        JLabel labelfName = new JLabel("Father's Name :");
        labelfName.setFont(new Font("Raleway",Font.BOLD,20));
        labelfName.setBounds(100,240,200,30);
        add(labelfName);

        text_father_name = new JTextField();
        text_father_name.setFont(new Font("Raleway",Font.BOLD,14));
        text_father_name.setBounds(300,240,400,30);
        add(text_father_name);

        JLabel DOB = new JLabel("Date of Birth :");
        DOB.setFont(new Font("Raleway",Font.BOLD,20));
        DOB.setBounds(100,340,200,30);
        add(DOB);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105,105,105));
        dateChooser.setBounds(300,340,400,30);
        add(dateChooser);

        JLabel labelG = new JLabel("Gender :");
        labelG.setFont(new Font("Raleway",Font.BOLD,20));
        labelG.setBounds(100,290,200,30);
        add(labelG);

        male = new JRadioButton("Male");
        male.setFont(new Font("Raleway",Font.BOLD,14));
        male.setBackground(new Color(222,255,228));
        male.setBounds(300,290,60,30);
        add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Raleway",Font.BOLD,14));
        female.setBackground(new Color(222,255,228));
        female.setBounds(450,290,90,30);
        add(female);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);

        JLabel labelEmail = new JLabel("Email Address :");
        labelEmail.setFont(new Font("Raleway",Font.BOLD,20));
        labelEmail.setBounds(100,390,200,30);
        add(labelEmail);

        text_email = new JTextField();
        text_email.setFont(new Font("Raleway",Font.BOLD,14));
        text_email.setBounds(300,390,400,30);
        add(text_email);

        JLabel labelMs = new JLabel("Marital Status :");
        labelMs.setFont(new Font("Raleway",Font.BOLD,20));
        labelMs.setBounds(100,440,200,30);
        add(labelMs);

        married = new JRadioButton("Married");
        married.setFont(new Font("Raleway",Font.BOLD,14));
        married.setBackground(new Color(222,255,228));
        married.setBounds(300,440,100,30);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setFont(new Font("Raleway",Font.BOLD,14));
        unmarried.setBackground(new Color(222,255,228));
        unmarried.setBounds(450,440,100,30);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setFont(new Font("Raleway",Font.BOLD,14));
        other.setBackground(new Color(222,255,228));
        other.setBounds(635,440,100,30);
        add(other);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(married);
        buttonGroup1.add(unmarried);
        buttonGroup1.add(other);

        JLabel labelAdd = new JLabel("Address :");
        labelAdd.setFont(new Font("Raleway",Font.BOLD,20));
        labelAdd.setBounds(100,490,200,30);
        add(labelAdd);

        text_address = new JTextField();
        text_address.setFont(new Font("Raleway",Font.BOLD,14));
        text_address.setBounds(300,490,400,30);
        add(text_address);

        JLabel labelCity = new JLabel("City :");
        labelCity.setFont(new Font("Raleway",Font.BOLD,20));
        labelCity.setBounds(100,540,200,30);
        add(labelCity);

        text_city = new JTextField();
        text_city.setFont(new Font("Raleway",Font.BOLD,14));
        text_city.setBounds(300,540,400,30);
        add(text_city);

        JLabel labelPin = new JLabel("Pincode :");
        labelPin.setFont(new Font("Raleway",Font.BOLD,20));
        labelPin.setBounds(100,590,200,30);
        add(labelPin);

        text_pin = new JTextField();
        text_pin.setFont(new Font("Raleway",Font.BOLD,14));
        text_pin.setBounds(300,590,400,30);
        add(text_pin);

        JLabel labelState = new JLabel("State :");
        labelState.setFont(new Font("Raleway",Font.BOLD,20));
        labelState.setBounds(100,640,200,30);
        add(labelState);

        text_state = new JTextField();
        text_state.setFont(new Font("Raleway",Font.BOLD,14));
        text_state.setBounds(300,640,400,30);
        add(text_state);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD,14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(620,710,80,30);
        next.addActionListener(this);
        add(next);






        getContentPane().setBackground(new Color(222,255,228));
        setLayout(null);
        setSize(850,800);
        setLocation(360,40);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String formno = first;
        String name = text_name.getText();
        String father_name = text_father_name.getText();
        String dob = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(male.isSelected())
        {
            gender = "Male";
        } else if (female.isSelected())
        {
            gender = "Female";
        }
        String email = text_email.getText();
        String marital = null;
        if(married.isSelected())
        {
            marital = "Married";
        } else if (unmarried.isSelected())
        {
            marital = "Unmarried";
        } else if (other.isSelected())
        {
            marital = "Other";
        }
        String address = text_address.getText();
        String city = text_city.getText();
        String pincode = text_pin.getText();
        String state = text_state.getText();
        try
        {
            if(text_name.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            }else
            {
                Jdbc con1 = new Jdbc();
                String query = "insert into signup values('"+formno+"', '"+name+"', '"+father_name+"', '"+dob+"', '"+gender+"', '"+email+"', '"+marital+"', '"+address+"', '"+city+"', '"+pincode+"', '"+state+"')";
                con1.statement.executeUpdate(query);
                new Signup2(first);
                setVisible(false);
            }

        }
        catch (Exception E)
        {
            E.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        new Signup();
    }
}
