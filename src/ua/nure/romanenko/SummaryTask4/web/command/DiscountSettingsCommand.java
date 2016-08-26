package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.dao.DiscountDAO;
import ua.nure.romanenko.SummaryTask4.db.entity.Discount;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Discount settings command
 * Created by Роман on 26.08.2016.
 */
public class DiscountSettingsCommand extends Command {
    private static final long serialVersionUID = 40663402723746L;

    private static final Logger LOG = Logger.getLogger(DiscountSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.trace("DiscountSettingsCommand starts");


//        int max = (int) request.getAttribute("max");
//        LOG.trace("Catch max --> " + max);
//        int step = (int) request.getAttribute("step");
//        LOG.trace("Catch step --> " + step);
//
//        DiscountDAO.updateDiscount(max, step);

        LOG.trace("DiscountSettingsCommand finished");
        return Path.PAGE_DISCOUNT_SETTINGS;
    }
}
