package org.example;

import org.model.Department;
import org.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    private static final String XML_FILE = "departments.xml";

    public static void main(String[] args) {
        Employee emp1 = new Employee("20160922", "Nguyen Bich Van", null);
        Employee emp2 = new Employee("20150724", "Nguyen Viet Hung", "20160922");
        Employee emp3 = new Employee("20140821", "Nguyen Tien Nam", null);
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(emp1);
        employees.add(emp2);
        employees.add(emp3);
        Department department = new Department("SALES", "ACCOUNTING", "HANOI");
        department.setEmployees(employees);
        try {
            JAXBContext context = JAXBContext.newInstance(Department.class);

            // Marshalling from Java object to file.
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(department, System.out);
            File file = new File(XML_FILE);
            // Write to file.
            marshaller.marshal(department, file);

            // Write to console screen.
            System.out.println("Write to file: " + file.getAbsolutePath());

            // Unmarshalling from file to console screen.
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Department deptFromFile = (Department) unmarshaller.unmarshal(new FileReader(XML_FILE));
            List<Employee> employeeList = deptFromFile.getEmployees();
            // Print to console screen.
            for (Employee employee : employeeList) {
                System.out.println("Employee: " + employee.getEmpName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Result:

// <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
// <ns2:department xmlns:ns2="http://example.com/jaxb">
// <deptNo>SALES</deptNo>
// <deptName>ACCOUNTING</deptName>
// <employees>
// <employee>
// <empNo>20160922</empNo>
// <empName>Nguyen Bich Van</empName>
// </employee>
// <employee>
// <empNo>20150724</empNo>
// <empName>Nguyen Viet Hung</empName>
// <managerNo>20160922</managerNo>
// </employee>
// <employee>
// <empNo>20140821</empNo>
// <empName>Nguyen Tien Nam</empName>
// </employee>
// </employees>
// </ns2:department>
//        Write to file: E:\github\sample_jaxb_java7\departments.xml
//        Employee: Nguyen Bich Van
//        Employee: Nguyen Viet Hung
//        Employee: Nguyen Tien Nam
//        Disconnected from the target VM, address: '127.0.0.1:51287', transport: 'socket'
//
//        Process finished with exit code 0
