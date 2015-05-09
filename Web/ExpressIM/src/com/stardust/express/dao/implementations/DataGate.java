package com.stardust.express.dao.implementations;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import com.stardust.express.models.DataModel;


public class DataGate{
	protected static SessionFactory sessionFactory;  
	protected String modelName = "User";
    static{  
        try{  
            Configuration conf = new Configuration();  
            conf.configure();  
            ServiceRegistry sr = new ServiceRegistryBuilder()  
                                    .applySettings(conf.getProperties())  
                                    .buildServiceRegistry();  
              
            sessionFactory = conf.buildSessionFactory(sr);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    protected Session getSession() {
    	return sessionFactory.openSession();
    }
    
    public DataGate() {
    	
    }
    
    public DataModel find(int id) {
    	Session session = getSession(); 
    	
    	try{  
    		String hql = "from " + modelName + " where ID = :ID";
            Query query = session.createQuery(hql);
            query.setInteger("ID", id);
            
            @SuppressWarnings("unchecked")
			List<DataModel> list = query.list();
            if (list.size() > 0) {
            	return list.get(0);
            }
        } catch (Exception e) {  
        	e.printStackTrace();  
        } finally {  
            session.close();  
        }  
        return null;
    }
    
    public void remove(DataModel model) {
    	Session session = getSession();
    	Transaction transaction = null;  
    	
    	try{  
    		transaction = session.beginTransaction();   
            session.delete(model);  
            transaction.commit();  
        } catch (Exception e) {  
            if(null != transaction){  
            	transaction.rollback();  
            }  
            e.printStackTrace();  
        } finally {  
            session.close();  
        }  
    }
    
    public void add(DataModel model) {
    	Session session = getSession();
    	Transaction transaction = null;  
    	
    	try{  
    		transaction = session.beginTransaction();   
            session.save(model);  
            transaction.commit();  
        } catch (Exception e) {  
            if(null != transaction){  
            	transaction.rollback();  
            }  
            e.printStackTrace();  
        } finally {  
            session.close();  
        }  
    }
}
