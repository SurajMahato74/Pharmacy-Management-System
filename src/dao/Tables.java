/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author Su Raj
 */
public class Tables {
  public static void main(String[] args){
      try{
      Connection con = ConnectionProvider.getCon();
      Statement st= con.createStatement();
    //  st.executeUpdate("create table appuser(appuser_pk int AUTO_INCREMENT primary key,userRole varchar(200),name varchar(200),dob varchar(50),mobileNumber varchar(50),email varchar(200),username varchar(200),password varchar(50),address varchar(200))");
    //  st.executeUpdate("insert into appuser (userRole,name,dob,mobileNumber,email,username,password,address) values('Admin','Admin','16-12-1992','0000111122','admin@email.com','admin','admin','Nepal')");
    //  st.executeUpdate("create table medicine(medicine_pk int AUTO_INCREMENT primary key,uniqueId varchar(200),companyName varchar(200),quantity bigint,price bigint)");
    //  JOptionPane.showMessageDialog(null,"Table Created Sucessfully");
       st.executeUpdate("ALTER TABLE bill ADD quantity bigint");
     // st.executeUpdate("create table bill(bill_pk int AUTO_INCREMENT primary key, billId varchar(200),billDate varchar(50),totalPaid bigint, generatedBy varchar(50))");
     // st.executeUpdate("ALTER TABLE appuser ADD status varchar(50) DEFAULT 'Pending'");
         /* st.executeUpdate("ALTER TABLE medicine " +
                "ADD supplier varchar(200), " +
                "ADD mfg DATE, " +
                "ADD exp DATE, " +
                "ADD type varchar(200), " +
                "ADD location varchar(200)");*/
     //    st.executeUpdate("ALTER TABLE medicine MODIFY quantity INT ");


JOptionPane.showMessageDialog(null,"Table Updated Sucessfully");
  }  
      
  catch(Exception e){
    JOptionPane.showMessageDialog(null,e);
}
  }
}
