package ru.job4j.dream.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class CandidateDeleteServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Candidate candidate = PsqlStore.instOf().findCandidateById(Integer.parseInt(id));
            if (candidate.getPhotoName() != null) {
                String folderName = "images" + File.separator + "photo";
                File file = new File(folderName + File.separator + candidate.getPhotoName());
                if (file.delete()) {
                    LOG.debug(file + " удален");
                } else {
                    LOG.debug(file + " ошибка");
                }
            }
            PsqlStore.instOf().delete(candidate);
        }
        resp.sendRedirect(req.getContextPath() + "/candidate.do");
    }
}