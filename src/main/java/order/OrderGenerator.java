package order;

import models.Courier;
import models.Order;

import java.time.LocalDate;

import static utils.Utils.randomInt;
import static utils.Utils.randomString;

public class OrderGenerator {
    public static Order randomOrder() {
            String firstName = randomString(10);
            String lastName = randomString(10);
            String address = randomString(16);
            String metroStation = randomString(16);
            String phone = randomString(12);
            int rentTime = randomInt(4);
            String deliveryDate = LocalDate.of(4, 12, 17).toString();
            String comment = randomString(20);
            String[] color = {};
            return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }
}
