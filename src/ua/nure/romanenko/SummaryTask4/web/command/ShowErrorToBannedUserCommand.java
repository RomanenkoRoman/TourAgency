package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ShowErrorToBannedUserCommand
 * Created by Роман on 27.08.2016.
 */
public class ShowErrorToBannedUserCommand extends Command{

    private static final long serialVersionUID = -2732415222446657267L;

    private static final Logger LOG = Logger.getLogger(ShowErrorToBannedUserCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("ShowErrorToBannedUserCommand starts");


        LOG.debug("ShowErrorToBannedUserCommand finished");
        return Path.PAGE_YOU_ARE_BANNED;
    }

}
