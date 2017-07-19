import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import testuniverse.easyqa.tests.CardDate;

public class SriptsToCode {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void SriptsToCode() {
        //логин по параметрам
        userLogin("kon.rudnev@gmail.com", "Cherepuha2");
        gotoProjectList();
        selectProject();
        gotoBugReacker();
        //карточка по параметрам
        createCardOnTracker(new CardDate("test card1 name1", "test desc1"));


    }
    //карточка в трекере
    public void createCardOnTracker(CardDate cardDate) {
        //создаем карточку
        wd.findElement(By.linkText("Создать карточку")).click();

        //название карточки
        wd.findElement(By.id("issue_summary")).click();
        wd.findElement(By.id("issue_summary")).sendKeys(cardDate.getCardName());

        //описание
        wd.findElement(By.id("issue_description")).click();
        wd.findElement(By.id("issue_description")).sendKeys(cardDate.getCardDescription());

        //выбираем приоритет

        clickAtSelectItem("issue_priority", "Высокий");

        clickAtSelectItem("type-view", "Задание");

        //сохраняем
        wd.findElement(By.name("commit")).click();
    }

    public void clickAtSelectItem(String selectId, String selectItem) {
        WebElement selectPriority = new RemoteWebElement();
        selectPriority = wd.findElement(By.id(selectId));
        selectPriority.click();
        selectPriority.findElement(By.linkText(selectItem)).click();
    }

    public void gotoBugReacker() {
        //кликаем по баг-трекеру
        wd.findElement(By.linkText("Баг Трекер")).click();
    }

    public void selectProject() {
        //кликаем по проекту
        wd.findElement(By.linkText("NewTest")).click();
    }

    public void gotoProjectList() {
        //кликаем по списку проектов
        wd.findElement(By.linkText("Мои Проекты")).click();
    }

    public void userLogin(String userLogin, String userPassword) {
        //переходим на страницу логина
        wd.get("https://app.geteasyqa.com/users/sign_in");
        //вводим емейл
        wd.findElement(By.id("user_email")).click();
        wd.findElement(By.id("user_email")).sendKeys(userLogin);

        //вводим пароль
        wd.findElement(By.id("user_password")).click();
        wd.findElement(By.id("user_password")).sendKeys(userPassword);

        //нажимаем кнопку войти
        wd.findElement(By.name("commit")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
