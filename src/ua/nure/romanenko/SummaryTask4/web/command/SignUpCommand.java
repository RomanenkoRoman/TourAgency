package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.Role;
import ua.nure.romanenko.SummaryTask4.db.dao.UserDAO;
import ua.nure.romanenko.SummaryTask4.db.entity.User;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sign in command
 * Created by Роман on 26.08.2016.
 */
public class SignUpCommand extends Command {
    private static final long serialVersionUID = 58493859046L;
    private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.trace("SignUpCommand starts");


        String firstName = request.getParameter("firstName");
        LOG.trace("find parameter firstName --> " + firstName);
        String lastName = request.getParameter("lastName");
        LOG.trace("find parameter lastName --> " + lastName);
        String login = request.getParameter("login");
        LOG.trace("find parameter login --> " + login);
        String password = request.getParameter("password");
        LOG.trace("find parameter password --> " + password);

        User user = new User(firstName, lastName, login, password, Role.USER.toString().toLowerCase(), false);


        boolean result = UserDAO.addUserToDataBase(user);
        if (result) {
            return Path.PAGE_SUCCESSFUL;
        }
        String c = "Choose another login";
        request.setAttribute("message", c);
        return Path.PAGE_SIGN_IN;
    }
}
