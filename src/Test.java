import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

interface Engine {
    public int getFuelCapacity();
}

class Car {

    //clasa interna normala
    class OttoEngine implements Engine {
        private int fuelCapacity;

        public OttoEngine(int fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
        }

        public int getFuelCapacity() {
            return fuelCapacity;
        }
    }

    //clasa interna normala cu constructor private
    class OttoEnginePrivate implements Engine {
        private int fuelCapacity;

        private OttoEnginePrivate(int fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
        }

        public int getFuelCapacity() {
            return fuelCapacity;
        }
    }

    //clasa interna statica
    static class StaticEngine implements Engine{
        public int fuelCapacity;
        public StaticEngine(int capacity) {
            this.fuelCapacity = capacity;
        }

        @Override
        public int getFuelCapacity() {
            return fuelCapacity;
        }
    }

    //clasa interna anonima
    public Engine getEngine(int fuelCapacity) {
        //implementare lambda
            return () -> fuelCapacity;
        //implementare normala

    //    return new Engine () {
    //        private int fuelCapacity = 11;
    //
    //        public int getFuelCapacity() {
    //            return fuelCapacity;
    //        }
    //    };
    }

    //clasa interna in interiorul unei metode
    public Engine getEngine_2() {
        class OttoEngine implements Engine {
            private int fuelCapacity = 13;

            public int getFuelCapacity() {
                return fuelCapacity;
            }
        }

        return new OttoEngine();
    }

    //clasa interna in interiorul unui bloc de tip if
    // (clasa va fi creata indiferent de valaorea de adevar din cadrul if ului)
    public Engine getEngine_3(int fuelCapacity) {
        if (fuelCapacity > 0) {
            class OttoEngine implements Engine {
                private int fuelCapacity = 11;

                public int getFuelCapacity() {
                    return fuelCapacity;
                }
            }

            return new OttoEngine();
        }

        return null;
    }

    public OttoEngine getEngine() {
        OttoEngine engine = new OttoEngine(11);
        return engine;
    }
    //desi constructorul e private acesta este vizibil din interiorul clasei externe
    public  OttoEnginePrivate getEnginePrivate(){
        OttoEnginePrivate privateEngine = new OttoEnginePrivate(123);
        return privateEngine;
    }
}

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        Car.OttoEngine firstEngine = car.getEngine();
        Car.OttoEnginePrivate privateEngine = car.getEnginePrivate();
        Car.OttoEngine secondEngine = car.new OttoEngine(10);

        System.out.println(new Car.StaticEngine(420).getFuelCapacity());
        System.out.println(privateEngine.getFuelCapacity());
        System.out.println(car.getEngine(11).getFuelCapacity());
        System.out.println(car.getEngine_2().getFuelCapacity());
        System.out.println(car.getEngine_3(12).getFuelCapacity());
        System.out.println(firstEngine.getFuelCapacity());
        System.out.println(secondEngine.getFuelCapacity());

        //sorting an arraylist using lambdas
        ArrayList<Integer> array_test = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Random random = new Random();
            int intt2 = random.nextInt();
            array_test.add(abs(intt2) % 100);
        }
        for (Integer intt: array_test) {
            System.out.print(intt + " ");
        }
        System.out.println("\n");
        array_test.sort((x, y)-> x - y);
        for (Integer intt: array_test) {
            System.out.print(intt + " ");
        }
        System.out.println("\n");
        //--------------------------------------------------
        //folosirea lambda in interiorul stream urilor
        List<Integer> numbers = Arrays.asList(8, 7, 6, 5, 4, 3, 2);
        List<Integer> square = numbers.stream().map(x->x*x).filter(x -> x> 4).sorted().toList();
        //fiecare metoda folosita returneaza alt stream
        List<Integer> square2 = numbers.stream().filter(x -> x > 4).map(x -> x * x).toList();
        //ordinea apelarii metodelor conteaza

        for (Integer x: square) {
            System.out.print(x + " ");
        }
        System.out.println("\n");

        for (Integer x: square2) {
            System.out.print(x + " ");
        }
        System.out.println("\n");


    }
}