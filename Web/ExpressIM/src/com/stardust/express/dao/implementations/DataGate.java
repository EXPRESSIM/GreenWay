package com.stardust.express.dao.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.stardust.express.dao.abstracts.IDataGate;
import com.stardust.express.models.DataModel;
import com.stardust.express.models.Datasource;
import com.stardust.express.models.User;


public abstract class DataGate implements IDataGate{
	protected static SessionFactory sessionFactory;  
	protected String datasource = "";
	protected static HashMap<String, SessionFactory> connections = new HashMap<String, SessionFactory>();
	
    static{  
        try{  
            Configuration conf = new Configuration();  
            conf.configure();  
            ServiceRegistry sr = new ServiceRegistryBuilder()  
                                    .applySettings(conf.getProperties())  
                                    .buildServiceRegistry();  
            
            sessionFactory = conf.buildSessionFactory(sr);  
            
            initializeConnections(conf);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
	protected static void initializeConnections(Configuration conf){
		Session session = sessionFactory.openSession(); 
		try{  
    		String hql="from Datasource";
    		Query query=session.createQuery(hql);
    		
            @SuppressWarnings("unchecked")
			List<Datasource> datasources = query.list();
            for (Datasource datasource : datasources)  { 
                
            	
            	// Modify configuration
            	String url = conf.getProperty("hibernate.connection.url");
            	String newUrl = url.replace(url.substring(url.indexOf(";databaseName=")),";databaseName=" + datasource.getDatabaseName());
            	conf.setProperty("hibernate.connection.url", newUrl);
            	
            	ServiceRegistry sr = new ServiceRegistryBuilder()  
                .applySettings(conf.getProperties())  
                .buildServiceRegistry();
            	
            	// Create session factory
            	SessionFactory factory = conf.buildSessionFactory(sr);
            	
            	// Push factory to map
            	connections.put(datasource.getDatasourceName(), factory);
            	
            	// Restore configuration
            	conf.setProperty("hibernate.connection.url", url);
            }
        } catch (Exception e) {  
        	e.printStackTrace();  
        } finally {  
            session.close();  
        }  
	}
    
    protected Session getSession() {
    	if (datasource == null || datasource.isEmpty()) {
    		return sessionFactory.openSession();
    	} else {
    		SessionFactory factory = connections.get(datasource);
    		if (factory == null) {
    			return sessionFactory.openSession(); 
    		} else {
    			return factory.openSession();
    		}
    	}
    }
    
    public DataGate(){
    	datasource = "";
    }
    
    public DataGate(String datasource) {
    	this.datasource = datasource;
    }
    
    public void setDatasource(String datasource) {
    	this.datasource = datasource;
    }
    
    public String getDatasource() {
    	return datasource;
    }
    
    public List<DataModel> fetchAll(){
    	Session session = getSession(); 
    	
    	try{  
    		Criteria c = session.createCriteria(User.class);
            
            @SuppressWarnings("unchecked")
			List<DataModel> list = c.list();
            if (list.size() > 0) {
            	return list;
            }
        } catch (Exception e) {  
        	e.printStackTrace();  
        } finally {  
            session.close();  
        }  
        return new ArrayList<DataModel>();
    }
    
    public DataModel find(long id) {
    	Session session = getSession(); 
    	
    	try{  
    		Criteria c = session.createCriteria(User.class);
    		c.add(Restrictions.eq("id",id));
            
            @SuppressWarnings("unchecked")
			List<DataModel> list = c.list();
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
    
    public void update(DataModel model) {
    	Session session = getSession();
    	Transaction transaction = null;  
    	
    	try{  
    		transaction = session.beginTransaction();   
            session.update(model);  
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
    
    abstract protected Class<?> getModelClass();
}
