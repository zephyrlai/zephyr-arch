package factory.simple;

/**
 * @ClassName: SimpleFactoryDemo
 * @Author: laizonghao
 * @Description:
 * @Date: 2019-08-12 10:27
 */
public class SimpleFactory {
    public Car produceCar(String carBrand){
        if(carBrand.equals("auto"))
            return new AutoCar();
        else if(carBrand.equals("tesla")){
            return new TeslaCar();
        }
        else
            return null;
    }
}
