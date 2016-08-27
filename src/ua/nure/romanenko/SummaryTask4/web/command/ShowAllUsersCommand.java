package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.dao.UserDAO;
import ua.nure.romanenko.SummaryTask4.db.entity.CatalogItem;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Show all users
 * Created by Роман on 27.08.2016.
 */
public class ShowAllUsersCommand extends Command {
    private static final long serialVersionUID = 312435678345222473L;

    private static final Logger LOG = Logger.getLogger(ShowAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("ShowAllUsersCommand starts");

        HttpSession session = request.getSession();
        List<User> userList = UserDAO.findAllUsers();

        session.setAttribute("userList",userList);

        LOG.trace("ShowAllUsersCommand finished");
        return Path.PAGE_LIST_USERS;
    }
}
