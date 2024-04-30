import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollSystem extends JFrame implements ActionListener {
    private JTextField nameField, hourlyRateField, hoursWorkedField;
    private JButton addButton, calculateButton, generateButton;
    private JTextArea outputArea;
    private List<Employee> employees;

    public EmployeePayrollSystem() {
        setTitle("Employee Payroll System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 20, 200, 25);
        panel.add(nameField);

        JLabel hourlyRateLabel = new JLabel("Hourly Rate:");
        hourlyRateLabel.setBounds(20, 50, 80, 25);
        panel.add(hourlyRateLabel);

        hourlyRateField = new JTextField();
        hourlyRateField.setBounds(100, 50, 100, 25);
        panel.add(hourlyRateField);

        addButton = new JButton("Add Employee");
        addButton.setBounds(220, 50, 150, 25);
        addButton.addActionListener(this);
        panel.add(addButton);

        JLabel hoursWorkedLabel = new JLabel("Hours Worked:");
        hoursWorkedLabel.setBounds(20, 80, 100, 25);
        panel.add(hoursWorkedLabel);

        hoursWorkedField = new JTextField();
        hoursWorkedField.setBounds(130, 80, 100, 25);
        panel.add(hoursWorkedField);

        calculateButton = new JButton("Calculate Salary");
        calculateButton.setBounds(20, 120, 150, 25);
        calculateButton.addActionListener(this);
        panel.add(calculateButton);

        generateButton = new JButton("Generate Pay Stub");
        generateButton.setBounds(180, 120, 150, 25);
        generateButton.addActionListener(this);
        panel.add(generateButton);

        outputArea = new JTextArea();
        outputArea.setBounds(20, 160, 350, 100);
        panel.add(outputArea);

        employees = new ArrayList<>();

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            double hourlyRate = Double.parseDouble(hourlyRateField.getText());
            employees.add(new Employee(name, hourlyRate));
            nameField.setText("");
            hourlyRateField.setText("");
        } else if (e.getSource() == calculateButton) {
            double totalPayroll = 0;
            for (Employee employee : employees) {
                double hoursWorked = Double.parseDouble(JOptionPane.showInputDialog("Enter hours worked for " + employee.getName()));
                employee.setHoursWorked(hoursWorked);
                totalPayroll += employee.calculateSalary();
            }
            outputArea.setText("Total Payroll: $" + totalPayroll);
        } else if (e.getSource() == generateButton) {
            String name = nameField.getText();
            for (Employee employee : employees) {
                if (employee.getName().equals(name)) {
                    double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
                    employee.setHoursWorked(hoursWorked);
                    outputArea.setText(employee.generatePayStub());
                    return;
                }
            }
            outputArea.setText("Employee not found!");
        }
    }

    public static void main(String[] args) {
        new EmployeePayrollSystem();
    }
}

class Employee {
    private String name;
    private double hourlyRate;
    private double hoursWorked;

    public Employee(String name, double hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    public String getName() {
        return name;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    public String generatePayStub() {
        return "Pay Stub for " + name + ":\nTotal Salary: $" + calculateSalary();
    }
}
