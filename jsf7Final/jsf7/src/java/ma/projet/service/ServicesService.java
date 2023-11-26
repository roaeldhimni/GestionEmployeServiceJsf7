/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;
import org.hibernate.Session;
import java.util.List;
import ma.projet.beans.Services;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
/**
 *
 * @author HP
 */public class ServicesService implements IDao<Services>{
    @Override
    public boolean create(Services o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean update(Services o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean delete(Services o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Services getById(int id) {
        Services salle  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        salle  = (Services) session.get(Services.class, id);
        session.getTransaction().commit();
        return salle;
    }

    @Override
    public List<Services> getAll() {
        
         List <Services> salles = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        salles  = session.createQuery("from Services").list();
        session.getTransaction().commit();
        return salles;
    }
    
    
}

