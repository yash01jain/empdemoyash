package main;

 
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
 
 
public class PolicyServiceFactory extends ODataJPAServiceFactory {
 
  private static final String PERSISTENCE_UNIT_NAME = "default";
  
  @Override
  public ODataJPAContext initializeODataJPAContext()
      throws ODataJPARuntimeException {
	
    ODataJPAContext oDatJPAContext = this.getODataJPAContext();
    oDatJPAContext.setDefaultNaming(false);
    
    try {
    
   	  oDatJPAContext.setEntityManagerFactory(PolicyContextLoader.emf);
      oDatJPAContext.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
 
      return oDatJPAContext;
 
    } catch (Exception e) {
 
      throw new RuntimeException(e);
    }
    finally {
    
    }
 
  }
  
  
 
 
}