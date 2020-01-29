package org.ocuryb.oriontek;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase1 {
    //Instanciacion del objeto WebDriver
    static WebDriver driver;
    public static void main(String[] args) {


       try{


           //Localizacion de chromedriver
           System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");

           //Variables a utilizar
           String urlInicial = "https://www.amazon.com/";
           String primerPrecio;
           String segundoPrecio;

           //Asignacion e inicializacion delnavegador y del driver utilizado en este caso
           driver = new ChromeDriver();

           //Navegar a la pagina indicada
           driver.get(urlInicial);

           //Probar la funcionalidad del carrito agregando un objeto y comparando el precio antes y despues de agreado
           WebElement txtBuscar = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
           WebElement btnBuscar = driver.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input"));
           txtBuscar.sendKeys("Acer Aspire 5 Slim Laptop, Plateado");
           btnBuscar.click();

           WebElement lblObjeto = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[4]/div[1]/div[3]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span"));
           lblObjeto.click();

           WebElement lblPrecio = driver.findElement(By.xpath("//*[@id=\"priceblock_ourprice\"]"));
           WebElement btnCarro = driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]"));
           primerPrecio = lblPrecio.getText();
           btnCarro.click();
           try {
               Thread.sleep(5*1000);
           }catch(Exception e) {
               System.out.println(e);
           }
           btnCarro = driver.findElement(By.cssSelector("#attach-sidesheet-view-cart-button > span > input"));
           btnCarro.click();
           lblPrecio = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[1]/div[5]/div/div[2]/div[4]/form/div[2]/div[3]/div[4]/div/div[2]/p/span"));
           segundoPrecio = lblPrecio.getText();

           if(primerPrecio.equals(segundoPrecio)){
               System.out.println("Prueba exitosa, ambos precios son identicos: Precio inicial: "+primerPrecio+" , Precio en carrito: "+segundoPrecio);
           }else{
               System.out.println("Prueba no exitosa, los precios difieren: Precio inicial: "+primerPrecio+" , Precio en carrito: "+segundoPrecio);
           }




       } catch (NoSuchElementException ne){
           System.err.println("WebElement no encontrado: "+ne.getMessage());
        }catch (WebDriverException we){
            System.err.println("Fallo del WebDriver: "+we.getMessage());
       }catch (Exception ex){
            System.err.println(ex.getMessage());
       }finally{
            driver.quit();
        }









    }
}
