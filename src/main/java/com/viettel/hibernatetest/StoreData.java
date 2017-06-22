package com.viettel.hibernatetest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pm2-vdi-04
 */
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {

    public static void main(String[] args) {

        //creating configuration object  
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml").addAnnotatedClass(com.viettel.hibernatetest.NguyendungPersons.class);//populates the data of the configuration file  

        //creating seession factory object
        SessionFactory factory = null;
        Session session= null;
        try {
            factory = cfg.buildSessionFactory();
              //creating session object  
            session = factory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //creating transaction object  
        try {
            //begin add a person
            Transaction t= session.beginTransaction();
//            NguyendungPersons person= new NguyendungPersons(BigDecimal.valueOf(4), BigDecimal.valueOf(23), "HaiTest", "NguyenTest", "hanoi", "vietnam", "thanhhaibka");
//            session.persist(person);
//            t.commit();
            //end add a person
            //begin search person
            String hql = "from NguyendungPersons";
            Query query = session.createQuery(hql);
            List results = query.list();
            //end search
            System.out.println(results);

            //begin delete
            NguyendungPersons person= session.get(NguyendungPersons.class, BigDecimal.valueOf(4));
            session.delete(person);
            t.commit();
            //end delete
            //begin search person
            hql = "from NguyendungPersons";
            query = session.createQuery(hql);
            results = query.list();
            //end search
            System.out.println(results);
            session.close();
            System.out.println("successfully saved");
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
