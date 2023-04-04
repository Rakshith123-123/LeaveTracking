package com.boomi.leavetracking.dao;

import com.boomi.leavetracking.models.Attendance;
import com.boomi.leavetracking.models.Student;
import com.boomi.leavetracking.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentDao {

    public SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    public Student getByRollNo(String rollNo){
        Session session = sessionFactory.openSession();
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            Query query=session.createQuery("from Student S where S.rollNo =: rollNo");
            query.setParameter("rollNo", rollNo);
            List<Student> students = query.getResultList();
            Student student = students.get(0);
            tx.commit();
            return student;

        }catch (HibernateException e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
    public boolean createStudent(String name, String rollNo, String department){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Student student = new Student();
            student.setName(name);
            student.setRollNo(rollNo);
            student.setDepartment(department);
            session.save(student);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean updateStudent(Student student) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean updated = false;
        try {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            updated = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return updated;
    }

    public boolean deleteStudent(String rollNo) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean deleted = false;
        try {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, rollNo);
            if (student != null) {
                session.delete(student);
                deleted = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return deleted;
    }

    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        Transaction transaction=null;
        List<Student> allStudents = new ArrayList<>();

        try {
            transaction=session.beginTransaction();
            List fromStudent = session.createQuery("From Student").list();
            Iterator iterator = fromStudent.iterator();
            while (iterator.hasNext()) {
                allStudents.add((Student) iterator.next());
            }
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return allStudents;
    }

}
