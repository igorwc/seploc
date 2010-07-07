package br.seploc.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceServiceFactory {
    
    /* EntityManagerFactory */  
    private static EntityManagerFactory emf ;  
      
    /* EntityManager */  
    private EntityManager        em  ;  
      
    /* Singleton PersistenceServiceFactory */  
    private static PersistenceServiceFactory instance = new PersistenceServiceFactory ();  
      
    private PersistenceServiceFactory(){  
    }  
    public static PersistenceServiceFactory getInstance() {  
        return instance;  
    }  
  
public static EntityManagerFactory create() {  
        try{  
            if (emf == null)  
                emf = Persistence.createEntityManagerFactory("seploc");  
        }catch(Exception e){  
            System.out.println("Não conseguiu acesar o Banco no metodo create() "+e);
            e.printStackTrace();
        }  
        return emf;   
    }  
  
public EntityManager getManager() {  
        try{  
            emf = create();  
            em  = emf.createEntityManager();  
        }catch(Exception e){  
            System.out.println("Não conseguiu acesar o Banco no metodo getManager() "+e); 
//            e.printStackTrace();
            e.printStackTrace(System.out);
        }  
        return em;    
    }
 public static void main(String[] args){
	 EntityManager em = PersistenceServiceFactory.getInstance().getManager();
 }
}
