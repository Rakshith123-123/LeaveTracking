package com.boomi.leavetracking.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boomi.leavetracking.models.User;
import com.boomi.leavetracking.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // check if user with given username already exists
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User existingUser = (User) session.createQuery("FROM User WHERE username = :username")
                .setParameter("username", username).uniqueResult();

        if (existingUser != null) {
            // user with given username already exists, redirect to registration page with error message
            tx.rollback();
            session.close();
            request.setAttribute("errorMessage", "User with this username already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // create new user object and persist in database
        User user = new User(username, password);
        session.save(user);
        tx.commit();
        session.close();

        // redirect to login page with success message
        request.setAttribute("successMessage", "Registration successful. Please login.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

