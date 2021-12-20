package commonFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommonFunctions {
    public WebDriver driver;
    String userWorkingDirectory = System.getProperty("user.dir");
    String pathSeparator = System.getProperty("file.separator");
    public List<String> veggies = new ArrayList<>();
    public List<String> quantity = new ArrayList<>();
    public List<String> country = new ArrayList<>();

    public void readExcelData() {
        List<String> headerNames;
        try {
            File file = new File(getXlsxFilePath());
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workBook.getSheetAt(0);
            int numberOfRows = sheet.getLastRowNum();
            if ((numberOfRows > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
                numberOfRows++;
            }
            Row headerRow = sheet.getRow(0);
            headerNames = new ArrayList<>();
            int numberOfColumns = headerRow.getLastCellNum();
            DataFormatter format = new DataFormatter();
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                headerNames.add(format.formatCellValue(headerRow.getCell(columnIndex)));
            }
            for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
                String header = headerNames.get(columnIndex);
                if (header.equals(getColumnOneHeader())) {
                    for (int rowIndex = 1; rowIndex < numberOfRows; rowIndex++) {
                        Row rowData = sheet.getRow(rowIndex);
                        veggies.add(format.formatCellValue(rowData.getCell(columnIndex)));
                    }
                } else if (header.equals(getColumnTwoHeader())) {
                    for (int rowIndex = 1; rowIndex < numberOfRows; rowIndex++) {
                        Row rowData = sheet.getRow(rowIndex);
                        quantity.add(format.formatCellValue(rowData.getCell(columnIndex)));
                    }
                } else if (header.equals(getColumnThreeHeader())) {
                    for (int rowIndex = 1; rowIndex < numberOfRows; rowIndex++) {
                        Row rowData = sheet.getRow(rowIndex);
                        country.add(format.formatCellValue(rowData.getCell(columnIndex)));
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Oops, Check if the file is present in the given location..");
        } catch (IOException exception) {
            System.out.println("Check the file");
        } catch (NullPointerException exception) {
            System.out.println("Sheet has null values");
        }
    }

    public String configFilePath() {
        return userWorkingDirectory + pathSeparator + "src" + pathSeparator + "test" +
                pathSeparator + "java" + pathSeparator + "commonFunctions"
                + pathSeparator + "files" + pathSeparator + "config.properties";
    }

    public Properties getPropertiesObject() {
        Properties property = new Properties();
        try {
            FileInputStream file = new FileInputStream(configFilePath());
            property.load(file);
        } catch (FileNotFoundException exception) {
            System.out.println("The specified file is not present in the given path.");
        } catch (IOException exception) {
            System.out.println("Check the file in the specified path.");
        }
        return property;
    }

    public String getXlsxFilePath() {
        return userWorkingDirectory + pathSeparator + "src" + pathSeparator + "test" +
                pathSeparator + "java" + pathSeparator + "commonFunctions"
                + pathSeparator + "files" + pathSeparator + getPropertiesObject().getProperty("xlsxFileName");
    }

    public String getUrl() {
        return getPropertiesObject().getProperty("url");
    }

    public String getColumnOneHeader() {
        return getPropertiesObject().getProperty("columnOneHeader");
    }

    public String getColumnTwoHeader() {
        return getPropertiesObject().getProperty("columnTwoHeader");
    }

    public String getColumnThreeHeader() {
        return getPropertiesObject().getProperty("columnThreeHeader");
    }

    public int numberOfVeggies() {
        return veggies.size();
    }

    @BeforeSuite
    public void launchBrowser() {
        readExcelData();
        String browserName = getPropertiesObject().getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get(getUrl());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}
