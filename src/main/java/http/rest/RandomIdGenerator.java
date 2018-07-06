package http.rest;

import java.util.Random;

/**
 * Created by krzysztof on 24.10.17.
 */
public class RandomIdGenerator {

    public static Integer generateRandomId(){
        return  Math.abs(new Random(System.currentTimeMillis())
                .nextInt());
    }

}
