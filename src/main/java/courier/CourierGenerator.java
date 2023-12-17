package courier;
import models.Courier;
import static utils.Utils.randomString;

public class CourierGenerator {
    public static Courier randomCourier(){
        return new Courier()
                .withLogin(randomString(8))
                .withPassword(randomString(16))
                .withFirstname(randomString(10));
    }
}
