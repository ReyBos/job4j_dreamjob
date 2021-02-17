package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Photo;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CandidatePhotoDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String candidateId = req.getParameter("candidate_id");
        if (id != null) {
            Photo photo = new Photo(Integer.parseInt(id));
            PsqlStore.instOf().delete(photo);
        }
        resp.sendRedirect(req.getContextPath() + "/candidate/edit.do?id=" + candidateId);
    }
}