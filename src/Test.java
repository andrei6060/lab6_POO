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
}

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        Car.OttoEngine firstEngine = car.getEngine();
        Car.OttoEngine secondEngine = car.new OttoEngine(10);


        System.out.println(car.getEngine(11).getFuelCapacity());
        System.out.println(car.getEngine_2().getFuelCapacity());
        System.out.println(car.getEngine_3(12).getFuelCapacity());
        System.out.println(firstEngine.getFuelCapacity());
        System.out.println(secondEngine.getFuelCapacity());
    }
}