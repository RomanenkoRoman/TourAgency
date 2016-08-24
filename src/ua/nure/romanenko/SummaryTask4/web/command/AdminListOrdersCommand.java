package ua.nure.romanenko.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.romanenko.SummaryTask4.Path;
import ua.nure.romanenko.SummaryTask4.db.DBManager;
import ua.nure.romanenko.SummaryTask4.db.bean.ExtendedUserOrderBean;
import ua.nure.romanenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * Created by Роман on 22.08.2016.
 */
public class AdminListOrdersCommand extends Command {
    private static final long serialVersionUID = 8254012378863123L;

    private static final Logger LOG = Logger.getLogger(AdminListOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("AdminListOrdersCommand starts");

        List<ExtendedUserOrderBean> list =
                DBManager.getInstance().getExtendedUserOrderBean();
        LOG.trace("Found in DB: extendedUserOrderBeanList --> " + list);

        HttpSession session = request.getSession();
        session.setAttribute("extendedUserOrderBeanList",list);
        request.setAttribute("extendedUserOrderBeanList",list);
        LOG.trace("Set the request attribute: extendedUserOrderBeanList --> "
                + list);

        LOG.debug("AdminListOrdersCommand finished");

        return Path.PAGE_LIST_ORDERS;
    }



}


