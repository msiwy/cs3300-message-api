package restful;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by magnussiwy on 6/24/15.
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    NotificationDao dao;

    public NotificationController() {
        this.dao = new NotificationDao();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Notification> getNotifications(@RequestParam("userId") int userId,
                                               @RequestParam("type") String type) {
        return this.dao.getNotifications(userId, type);
    }
}
