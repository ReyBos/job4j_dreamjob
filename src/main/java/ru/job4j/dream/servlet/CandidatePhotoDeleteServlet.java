package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class CandidatePhotoDeleteServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String candidateId = req.getParameter("candidate_id");
        if (id != null) {
            Photo photo = new Photo(Integer.parseInt(id));
            String folderName = "images" + File.separator + "photo";
            File file = new File(folderName + File.separator + photo.getId());
            if (file.delete()) {
                LOG.debug(file + " удален");
            } else {
                LOG.debug(file + " ошибка");
            }
            PsqlStore.instOf().delete(photo);
        }
        resp.sendRedirect(req.getContextPath() + "/candidate/edit.do?id=" + candidateId);
    }
}