package main;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PolicyContextLoader  implements ServletContextListener {
	
	private  Map<String, String> properties = new HashMap<String, String>(); 
	public static EntityManagerFactory emf;
	  	 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
		emf.close();
	}
 
        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");	
		try {
			setDBproperties();
			if(emf == null){
		    	 System.out.println("Trying to Build emf");
		    	 System.out.println(properties.get("hibernate.dialect"));
		         emf =  Persistence.createEntityManagerFactory("default", properties);
		   }
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			 System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	 public void setDBproperties() throws URISyntaxException{
	      System.out.println("getDBproperties");
	      URI dbUri = new URI(System.getenv("DATABASE_URL"));
	      System.out.println(dbUri.getUserInfo());
	      
	      String username = dbUri.getUserInfo().split(":")[0];
	      System.out.println(username);
	      String password = dbUri.getUserInfo().split(":")[1];
	      System.out.println(password);
	      String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	      System.out.println(dbUrl);
		  properties.put("javax.persistence.jdbc.url", dbUrl );
		  properties.put("javax.persistence.jdbc.user", username );
		  properties.put("javax.persistence.jdbc.password", password );
		  properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
		  properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");	
		  
		 
	  }

}
