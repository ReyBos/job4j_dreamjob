package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

@WebServlet(urlPatterns = {"/index.html"})
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Calendar from = new GregorianCalendar();
        from.set(Calendar.HOUR_OF_DAY, 0);
        from.set(Calendar.MINUTE, 0);
        from.set(Calendar.SECOND, 0);
        Calendar to = new GregorianCalendar();
        Collection<Post> posts = PsqlStore.instOf().findAllPostsBetweenDates(from, to);
        Collection<Candidate> candidates
                = PsqlStore.instOf().findAllCandidatesBetweenDates(from, to);
        req.setAttribute("posts", posts);
        req.setAttribute("candidates", candidates);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}