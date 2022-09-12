package com.ideas2it.singleton;

import java.sql.SQLException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ideas2it.singleton.ConnectionEstablishment;
import com.ideas2it.entity.Employee;
import com.ideas2it.entity.Trainee;
import com.ideas2it.entity.Trainer;
import com.ideas2it.dao.IEmployeeDao;
import java.io.File;


public class ConnectionEstablishment{
      
    private static Session session = null;
    private static ConnectionEstablishment  connectionEstablishment = null;
  
    private ConnectionEstablishment() {
	try {    
	    Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Trainer.class);
	    configuration.addAnnotatedClass(Trainee.class);
	
            ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(srvcReg);
	    session  = sessionFactory.openSession();
	}
	catch(Exception e ) {
	    e.printStackTrace();
	}
    }
	
   /**
    *
    *used to return the opened session
    *@return {@link session}
    */ 
    public static Session getConnection() {

	if ( session == null ) {
	    connectionEstablishment  = new  ConnectionEstablishment(); 
	}

	if (!session.isOpen()) {
            connectionEstablishment  = new  ConnectionEstablishment(); 
	}
	
	return session;
	
    }
}

  