package ObjectPackege;

import java.awt.*;

public class AirUnit extends Unit {

    protected int up=0;
    public AirUnit() {
        super();
        setLayout(new GridLayout(10,1));

    }

    public void floatShip(){
        new Thread(new Runnable() {
            public void run() {
                while (objectIsLive) {
                    if (isObjectIsStanding()) {
                        up++;
                        if (up <= 10) {
                            setLocation(getX() + 1, getY() + 1);

                        } else if  (up < 20) {
                            setLocation(getX() - 1, getY() - 1);


                        } else up = 0;


                    }
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
