package com.baeldung.jtademo.services;

import com.baeldung.jtademo.dto.TransferLog;
import com.daedafusion.hibernate.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.OrderBy;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuditService {

    

    //@Autowired
    public AuditService() {
        //this.jdbcTemplate = jdbcTemplate;
    }

    public void log(String fromAccount, String toAccount, BigDecimal amount) {
        //jdbcTemplate.update("insert into AUDIT_LOG(FROM_ACCOUNT, TO_ACCOUNT, AMOUNT) values ?,?,?", fromAccount, toAccount, amount);
    }
    
    public TransferLog lastTransferLog() {
    	 
    	SessionFactory sessFac = new Configuration().configure().buildSessionFactory(); 
    	
    	Session session = sessFac.getCurrentSession();
    	org.hibernate.Transaction tr = session.beginTransaction();
    	Criteria criteria = session.createCriteria(TransferLog.class);
    	
    	List transferLogs = criteria.list();
    	return (TransferLog) transferLogs.get(transferLogs.size() -1);
        
    }
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }
    public static Integer create(TransferLog e) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Successfully created " + e.toString());
        return null; //e.getFromAccountId();
 
    }
    public static void update(TransferLog tl) {
    	Session session = getSessionFactory().openSession();
    	session.beginTransaction();
    	TransferLog em = (TransferLog) session.load(TransferLog.class, tl.getId());
    	em.setFromAccountId(tl.getFromAccountId());
    	session.getTransaction().commit();
    	session.close();
    }
}
