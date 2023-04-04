package com.boomi.leavetracking.servlet;

import com.boomi.leavetracking.models.User;
import com.boomi.leavetracking.utility.HibernateUtility;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // check if the username and password are valid
        boolean isValidUser = authenticate(username, password);
        if (isValidUser) {
            // set the user session and redirect to home page
//            request.getSession().setAttribute("user", username);
//            //response.sendRedirect(request.getContextPath() + "/home.jsp");
//           //response.sendRedirect("http://localhost:8080/LeaveTracking_war_exploded/home.jsp");
//            request.getRequestDispatcher(request.getContextPath()+"/home.jsp").forward(request, response);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            request.getSession().setAttribute("username",username);
            requestDispatcher.forward(request,response);

        } else {
            // show error message and redirect back to login page
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    private boolean authenticate(String username, String password) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where username = :username and password = :password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            User user = (User) query.uniqueResult();
            tx.commit();
            return (user != null);
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

}
