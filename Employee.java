import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class Employee {    
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/employee_db";
        String username = "root";
        String password = "your_password";
        Connection con = null;
        try {
           con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner obj = new Scanner(System.in);
        while(true){
            System.out.println("------------------------------");
            System.out.println("1.Add Employee");
            System.out.println("2.View Employee");
            System.out.println("3.Search Employee ");
            System.out.println("4.Delete Employee ");
            System.out.println("5.Update Employee ");
            System.out.println("6.Exit");
            System.out.println("Enter your choice: ");
            System.out.println("------------------------------");
           int choice = obj.nextInt();
           switch(choice){
               case 1:
                   System.out.println("------------------------------");
                   System.out.println("Enter id:");
                   int id = obj.nextInt();
                   System.out.println("------------------------------");
                   obj.nextLine();
                   System.out.println("------------------------------");
                   System.out.println("Enter name:");
                   String name = obj.nextLine();
                   System.out.println("------------------------------");
                   System.out.println("Enter department:");
                   String department = obj.nextLine();
                   System.out.println("------------------------------");
                   System.out.println("Enter salary:");
                   double salary= obj.nextDouble();
                   System.out.println("------------------------------");
                   
                   try{
                   
                    String sql = "INSERT INTO employees VALUES(?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1,id);
                    pst.setString(2,name);
                    pst.setString(3,department);
                    pst.setDouble(4,salary);
                    pst.executeUpdate();                    
                    pst.close();
                    System.out.println("------------------------------");
                    System.out.println("Employee added successfully");
                    System.out.println("------------------------------");
                    
                   }
                   catch(Exception e1){
                    e1.printStackTrace();
                    System.out.println("Insert Failed");
                   }
                   break;
                case 2:
                   
                       try{
                       
                        String sql = "SELECT * FROM employees";
                        PreparedStatement pst = con.prepareStatement(sql);
                       ResultSet rs = pst.executeQuery();
                       while(rs.next()){
                        System.out.println("------------------------------");
                        System.out.println(rs.getInt("id"));
                        System.out.println(rs.getString("name"));
                        System.out.println(rs.getString("department"));
                        System.out.println(rs.getDouble("salary"));
                        System.out.println("Displayed successfully");
                        System.out.println("------------------------------");
                       }
                        rs.close();
                        pst.close();
                        
                        
                       }
                       catch(Exception e1){
                        e1.printStackTrace();
                        System.out.println("------------------------------");
                        System.out.println("Failed to display");
                        System.out.println("------------------------------");
                       }
                   
                  
                    break;
                case 3: 
                    System.out.print("Enter id:");
                    int searchId = obj.nextInt();
                             try{
                               
                                String sql = "SELECT * FROM employees WHERE id = ?";
                                PreparedStatement pst = con.prepareStatement(sql);
                                pst.setInt(1,searchId);
                                ResultSet rs = pst.executeQuery();
                                if(rs.next()){
                                    System.out.println("------------------------------");
                                    System.out.println(rs.getInt("id"));
                                    System.out.println(rs.getString("name"));
                                    System.out.println(rs.getString("department"));
                                    System.out.println(rs.getDouble("salary"));
                                    System.out.println("Search successful");
                                    System.out.println("------------------------------");
                                }
                                   else{
                                    System.out.println("------------------------------");
                                    System.out.println("not found");
                                    System.out.println("------------------------------");
                                   }
                                   rs.close();
                                   pst.close();
                                

                                
                             }
                             catch (Exception e1){
                                e1.printStackTrace();
                                System.out.println("------------------------------");
                                System.out.println("Search failed");
                                System.out.println("------------------------------");

                             }
                        
                    
                    break;
                case 4:
                        System.out.println("------------------------------");
                        System.out.print("Enter id:");
                        int deleteId = obj.nextInt();
                        System.out.println("------------------------------");
                       
                                try{
                                    
                                    String sql = "DELETE FROM employees WHERE id = ?";
                                    PreparedStatement pst = con.prepareStatement(sql);
                                    pst.setInt(1,deleteId);
                                   int row = pst.executeUpdate();
                                    
                                    System.out.println("------------------------------");
                                    if(row >0){
                                        System.out.println("Deleted successfully");
                                    }
                                    else{
                                        System.out.println("not found");
                                    }
                                    System.out.println("------------------------------");
                                    pst.close();
                                }
                                catch(Exception e1){
                                    e1.printStackTrace();
                                    System.out.println("------------------------------");
                                    System.out.println("Failed to delete from database");
                                    System.out.println("------------------------------");
                                }
                                 break;
            
                case 5:
                   
                        System.out.println("Enter id:");
                        int updateId = obj.nextInt();
                                obj.nextLine();
                                System.out.println("Updated name:");
                                String newName = obj.nextLine();
                                System.out.println("Updated department:");
                                String newDepartment = obj.nextLine();
                                System.out.println("Updated salary:");
                                double newSalary = obj.nextDouble();
                               
                                try{
                                    
                                    String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
                                    PreparedStatement pst = con.prepareStatement(sql);
                                    pst.setString(1,newName);
                                    pst.setString(2,newDepartment);
                                    pst.setDouble(3,newSalary);
                                    pst.setInt(4,updateId);
                                     int row = pst.executeUpdate();
                                    System.out.println("------------------------------");
                                    if(row >0){
                                        System.out.println("Updated successfully");
                                    }
                                    else{
                                        System.out.println("not found");
                                    }
                                    System.out.println("------------------------------");
                                    pst.close();
                                }
                                catch(Exception e1){
                                    e1.printStackTrace();
                                    System.out.println("------------------------------");
                                    System.out.println("Failed to update in database");
                                    System.out.println("------------------------------");
                                }
                                break;
                            
                      
                    
                case 6:
                    System.out.println("------------------------------");
                    System.out.println("Exited successfully");
                    System.out.println("------------------------------");
                    
   
                    try{
                         obj.close();
                         con.close();
                         }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    return;
                    
                default:
                    System.out.println("------------------------------");
                    System.out.print("not valid choice");
                    System.out.println("------------------------------");
                    break;
           }System.out.println();
           }
           
       
    }
}
