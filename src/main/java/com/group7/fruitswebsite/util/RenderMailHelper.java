package com.group7.fruitswebsite.util;

import com.group7.fruitswebsite.common.ApplicationContextProvider;
import com.group7.fruitswebsite.config.ApplicationConfig;
import com.group7.fruitswebsite.entity.DhOrder;
import com.group7.fruitswebsite.entity.DhOrderProduct;
import com.group7.fruitswebsite.entity.DhUser;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author duyenthai
 */
@Log4j
public class RenderMailHelper {
    private RenderMailHelper() {
    }

    public static String renderBodyMailOrder(String userName, Integer orderId) {
        Session session = ApplicationContextProvider.getApplicationContext().getBean(Session.class);
        try {
            Optional<DhUser> user = Optional.ofNullable(
                    (DhUser) session.createQuery("from DhUser  where username = :userName")
                            .setParameter("userName", userName)
                            .uniqueResult());
            if (!user.isPresent()) {
                return StringUtils.EMPTY;
            }
            Optional<DhOrder> order = Optional.ofNullable(
                    (DhOrder) session.createQuery("from DhOrder where id = :orderId and dhUser.username = :userName")
                            .setParameter("orderId", orderId)
                            .setParameter("userName", userName)
                            .uniqueResult());
            if (order.isPresent()) {
                return renderBodyFromOrder(order.get(), user.get());
            }
        } catch (Exception ex) {
            log.error("Error render mail body ", ex);
        } finally {
            session.close();
        }
        return StringUtils.EMPTY;
    }

    private static String renderBodyFromOrder(DhOrder dhOrder, DhUser dhUser) throws IOException {
        String body = FileUtils.readFileToString(new File(ApplicationConfig.INVOICE_MAIL_TEMPLATE), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(body)) {
            return StringUtils.EMPTY;
        }
        body = body.replace("{{customer}}", dhUser.getName());
        body = body.replace("{{orderCode}}", dhOrder.getCodeName());
        StringBuilder orderStringBuilder = new StringBuilder(
                "<br><table><thead><tr>" +
                        "<th>Product</th>" +
                        "<th>Quantity</th>" +
                        "<th>Price</th>" +
                        "<th>Total</th>" +
                        "</tr></thead>");
        for (DhOrderProduct orderProduct : dhOrder.getOrderProducts()) {
            orderStringBuilder.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>",
                    orderProduct.getName(), orderProduct.getQuantity(), orderProduct.getPrice(), orderProduct.getQuantity() * orderProduct.getPrice()));
        }
        orderStringBuilder.append("</table>");
        body = body.replace("{{body}}", orderStringBuilder.toString());
        body = body.replace("{{total}}", dhOrder.getTotal().toString());
        log.info(String.format("Render body email ok, order %s, body %s", dhOrder.getId(), orderStringBuilder));
        return body;
    }
}
