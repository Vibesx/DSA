package org.example;

public class FactoryExample {
    public static void testFactory() {
        Brand skoda = CarFactory.getCar(BrandNames.SKODA);
        Brand seat = CarFactory.getCar(BrandNames.SEAT);
        skoda.createSedan().createCar();
        skoda.createSuv().createCar();
        seat.createSedan().createCar();
        seat.createSuv().createCar();
    }
}

enum BrandNames {
    SKODA,SEAT;
}

interface Brand {
    Sedan createSedan();
    Suv createSuv();
}

interface Sedan {
    void createCar();
}

interface Suv {
    void createCar();
}

class Superb implements Sedan {
    public void createCar() {
        System.out.println("Created a Skoda Superb");
    }
}

class Exeo implements Sedan {
    public void createCar() {
        System.out.println("Created a Seat Exeo");
    }
}

class Karoq implements Suv {
    public void createCar() {
        System.out.println("Created a Skoda Karoq");
    }
}

class Ateca implements Suv {
    public void createCar() {
        System.out.println("Created a Seat Ateca");
    }
}

class Skoda implements Brand {
    public Sedan createSedan() {
        return new Superb();
    }
    public Suv createSuv() {
        return new Karoq();
    }
}

class Seat implements Brand {
    public Sedan createSedan() {
        return new Exeo();
    }
    public Suv createSuv() {
        return new Ateca();
    }
}

class CarFactory {
    public static Brand getCar(BrandNames brand) {
        return switch (brand) {
            case SKODA -> new Skoda();
            case SEAT -> new Seat();
        };
    }
}

//interface Car {
//    void buildCar();
//}
//
//class Mini implements Car {
//    public void buildCar() {
//        System.out.println("Polo");
//    }
//}
//
//class Sedan implements Car {
//    public void buildCar() {
//        System.out.println("Passat");
//    }
//}
//
//class Suv implements Car {
//    public void buildCar() {
//        System.out.println("Tuareg");
//    }
//}
//
//interface CarFactory {
//    Car getCar();
//}
//
//class MiniCarFactory implements CarFactory {
//    public Car getCar() {
//        return new Mini();
//    }
//}
//
//class SedanCarFactory implements CarFactory {
//    public Car getCar() {
//        return new Sedan();
//    }
//}
//
//class SuvCarFactory implements CarFactory {
//    public Car getCar() {
//        return new Suv();
//    }
//}
//
//
