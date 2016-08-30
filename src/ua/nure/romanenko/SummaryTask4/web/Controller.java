package ua.nure.romanenko.SummaryTask4.web;


import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.exception.AppException;
import ua.nure.romanenko.SummaryTask4.web.command.Command;
import ua.nure.romanenko.SummaryTask4.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * Main servlet controller.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2476578654264816L;

    private static final Logger LOG = Logger.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {

        LOG.debug("Controller starts");

        // extract command name from the request
        String commandName = request.getParameter("command");
        LOG.trace("Request parameter: command --> " + commandName);

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        LOG.trace("Obtained command --> " + command);


//         execute command and get forward address
        String forward = Path.PAGE_ERROR_PAGE;

        try {
            forward = command.execute(request, response);

        } catch (AppException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
        }
        LOG.trace("Forward address --> " + forward);

        LOG.debug("Controller finished, now go to forward address --> " + forward);

        // go to forward



        switch (commandName) {
            case "updateSettings":
                response.sendRedirect(Path.COMMAND_SHOW_SETTINGS_PAGE);
                break;
            case "confirmOrder":
                response.sendRedirect(Path.COMMAND_PRIVATE_OFFICE);
                break;
            case "addCatalogItem":
                response.sendRedirect(Path.COMMAND_SHOW_ADD_PAGE);
                break;
            default:
                request.getRequestDispatcher(forward).forward(request, response);
                break;
        }
    }


}