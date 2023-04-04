package com.boomi.leavetracking.dao;

import com.boomi.leavetracking.models.Attendance;
import com.boomi.leavetracking.models.Student;
import com.boomi.leavetracking.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AttendenceDao {

    public SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

    public boolean createAttendence(String rollNo, String date, String attendance){
        Session session = sessionFactory.openSession();
        Transaction transaction=null;
        try {
            transaction = session.beginTransaction();
            StudentDao studentDao=new StudentDao();
            Student student = studentDao.getByRollNo(rollNo);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//            String toDate = date;
//
//            //convert String to LocalDate
//            LocalDate localDate = LocalDate.parse(date, formatter);
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);

            Attendance attendance1 = new Attendance(student, localDate, attendance);
            session.save(attendance1);
            transaction.commit();
            return true;

        }catch (HibernateException e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
    public boolean updateAttendance(String rollNo, String date, String attendance) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            Query query = session.createQuery("from Attendance A where A.student.rollNo = :rollNo and A.date = :date");
            query.setParameter("rollNo", rollNo);
            query.setParameter("date", localDate);
            List<Attendance> attendanceObj =  query.list();
            for(Attendance attendanceInstants : attendanceObj ) {
                if (attendanceInstants != null) {
                    attendanceInstants.setAttendance(attendance);
                    session.update(attendanceInstants);
                    success = true;
                } else {
                    success =  false;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return success;
    }

    public boolean deleteAttendance(String rollNo, String date) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            Query query = session.createQuery("from Attendance A where A.student.rollNo = :rollNo and A.date = :date");
            query.setParameter("rollNo", rollNo);
            query.setParameter("date", localDate);
//            Attendance attendanceObj = (Attendance) query.uniqueResult();
//            if (attendanceObj != null) {
//                session.delete(attendanceObj);
//                tx.commit();
//                return true;
//            } else {
//                return false;
//            }
            List<Attendance> attendanceObj =  query.list();
            for(Attendance attendanceInstants : attendanceObj ) {
                if (attendanceInstants != null) {
                    session.delete(attendanceInstants);
                    success = true;
                } else {
                    success =  false;
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return success;
    }

    public List<Attendance> getAllAttendance() {
        return getAttendances(sessionFactory);
    }

    static List<Attendance> getAttendances(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction=null;
        List<Attendance> attendanceList = new ArrayList<>();

        try {
            transaction=session.beginTransaction();
            List fromAttendance = session.createQuery("From Attendance").list();
            Iterator iterator = fromAttendance.iterator();
            while (iterator.hasNext()) {
                attendanceList.add((Attendance) iterator.next());
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
        return attendanceList;
    }
}
