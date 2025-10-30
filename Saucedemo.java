package saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Saucedemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            // Login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                    .sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            // Add item
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();

            // Go to cart
            driver.findElement(By.className("shopping_cart_link")).click();

            // Verify item
            String actualName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("inventory_item_name"))).getText();
            String actualPrice = driver.findElement(By.className("inventory_item_price")).getText();

            System.out.println("Item Name: " + actualName);
            System.out.println("Price: " + actualPrice);

        } finally {
            driver.quit(); // ✅ ensures browser closes no matter what
        }
    }
}
