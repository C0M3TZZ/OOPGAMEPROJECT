package object;

import javax.imageio.ImageIO;

public class OBJ_001 extends SuperObject {
    public OBJ_001() {
        name = "OBJ_001";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false;
        pickUpable = true;
    }

    @Override
    public void action() {
        System.out.println("OBJ_001 Pick Up");
    }
}
