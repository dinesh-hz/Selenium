package utilities;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Bass_Class extends Driver_manger {

	// ------>Expilt wait----//
	public static WebElement waitForElementVisible(WebElement element) {
		return getwait().until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement waitForElementClickable(WebElement element) {
		return getwait().until(ExpectedConditions.elementToBeClickable(element));
	}

	public static boolean waitForTextToBePresent(WebElement element, String text) {
		return getwait().until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public static boolean waitForElementInvisible(WebElement element) {
		return getwait().until(ExpectedConditions.invisibilityOf(element));
	}

	public static void waitForPageLoad() {
	    getwait().until(driver ->
	            ((JavascriptExecutor) driver)
	                    .executeScript("return document.readyState")
	                    .equals("complete")
	    );
	}

	// (Fluent Wait)
	/*
	 * public static WebElement waitForelementLocter(By locator, int timeout, int
	 * pollingTime) { Wait<WebDriver> fluentWait = new FluentWait<>(getDriver())
	 * .withTimeout(Duration.ofSeconds(timeout))
	 * .pollingEvery(Duration.ofSeconds(pollingTime))
	 * .ignoring(NoSuchElementException.class); return
	 * fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator)); }
	 */

	/// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void set_URL(String APPURL) {

		getDriver().get(APPURL);

	}

	public static List<WebElement> findElements(By locator) {
		return getDriver().findElements(locator);
	}

	/// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void navigate(NavigationType type) {

		switch (type) {
		case BACK:
			getDriver().navigate().back();
			break;
		case FORWARD:
			getDriver().navigate().forward();
			break;
		case REFRESH:
			getDriver().navigate().refresh();
			break;
		default:

			throw new IllegalArgumentException("Navigation type cannot be null");
		}
	}

	public enum NavigationType {
		BACK, FORWARD, REFRESH
	}

	/// ////////////////////////////////////////////////////////////////////////////////////////////
	public static void navigateToURL(String url) {

		getDriver().navigate().to(url);
	}

	public static void sendKeys(WebElement element, String text) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			throw new RuntimeException("Failed to send keys to element" + e);
		}
	}

	public static String selectFromDropdown(WebElement element, DropDownType type, String value) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(element));

			Select select = new Select(element);

			switch (type) {
			case INDEX:
				select.selectByIndex(Integer.parseInt(value));
				break;

			case VALUE:
				select.selectByValue(value);
				break;

			case TEXT:
				select.selectByVisibleText(value);
				break;
			}

			return select.getFirstSelectedOption().getText();

		} catch (Exception e) {
			throw new RuntimeException("Dropdown selection failed", e);
		}
	}

	public enum DropDownType {

		INDEX, VALUE, TEXT

	}

	public static boolean isMultipleSelect(WebElement element) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(element));

			Select select = new Select(element);
			return select.isMultiple();

		} catch (Exception e) {
			throw new RuntimeException("Element is not a <select> dropdown", e);
		}
	}

	public static boolean isBrokenImage(WebElement element) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(element));

			Long naturalWidth = (Long) ((JavascriptExecutor) getDriver())
					.executeScript("return arguments[0].naturalWidth;", element);

			return (naturalWidth == null || naturalWidth == 0);

		} catch (Exception e) {
			throw new RuntimeException("Failed to verify image state", e);
		}

		/*
		 * sample of code this WebElement img = driver.findElement(...);
		 * 
		 * boolean uiBroken = isBrokenImage(img);
		 * 
		 * String src = img.getAttribute("src"); boolean apiBroken =
		 * isImageUrlBroken(src);
		 */
	}

	// ✅ Mouse and Actions
	public static void mouseHoverAndClick(WebElement element) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(element));
			getwait().until(ExpectedConditions.elementToBeClickable(element));

			Actions actions = new Actions(getDriver());
			actions.moveToElement(element).pause(Duration.ofMillis(500)).click().perform();

		} catch (Exception e) {
			throw new RuntimeException("Failed to hover and click on element", e);
		}
	}

	public static void mouseoverAndRightClick(WebElement element) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(element));
			getwait().until(ExpectedConditions.elementToBeClickable(element));

			new Actions(getDriver()).moveToElement(element).contextClick().perform();

		} catch (Exception e) {
			throw new RuntimeException("Failed to perform right click", e);
		}
	}

	public static void dragAndDrop(WebElement source, WebElement target) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(source));
			getwait().until(ExpectedConditions.visibilityOf(target));

			new Actions(getDriver()).dragAndDrop(source, target).perform();

		} catch (Exception e) {
			throw new RuntimeException("Drag and drop failed", e);
		}
	}

	// ------>only for dynamic elements-------->
	public static WebElement findelement(By webelementlocter) {

		WebElement element = getDriver().findElement(webelementlocter);

		return element;
	}

	public static void acceptAlert() {
		try {
			getwait().until(ExpectedConditions.alertIsPresent());
			getDriver().switchTo().alert().accept();
		} catch (Exception e) {
			throw new RuntimeException("Failed to accept alert", e);
		}
	}

	public static void dismissAlert() {
		try {
			getwait().until(ExpectedConditions.alertIsPresent());
			getDriver().switchTo().alert().dismiss();
		} catch (Exception e) {
			throw new RuntimeException("Failed to dismiss alert", e);
		}
	}

	public static String getAlertText() {
		try {
			getwait().until(ExpectedConditions.alertIsPresent());
			return getDriver().switchTo().alert().getText();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get alert text", e);
		}
	}

	public static void sendKeysToAlert(String value) {
		getDriver().switchTo().alert().sendKeys(value);
	}

	// ✅ Frame and Window Handling
	public static int switchToFrameByIndex(String tagName, int index) {
		try {
			List<WebElement> frames = getDriver().findElements(By.tagName(tagName));

			if (frames.isEmpty()) {
				throw new NoSuchFrameException("No frames found with tag name: " + tagName);
			}

			if (index < 0 || index >= frames.size()) {
				throw new IndexOutOfBoundsException(
						" Invalid frame index: " + index + ". Total frames: " + frames.size());
			}

			WebElement frameElement = frames.get(index);

			getDriver().switchTo().frame(frameElement);

			return frames.size();
		} catch (Exception e) {
			throw new RuntimeException("ailed to switch to iframe using index: ", e);
		}
	}

	public static WebDriver switchToFrame(By locator) {
		WebElement frame = getwait().until(ExpectedConditions.visibilityOfElementLocated(locator));

		return getDriver().switchTo().frame(frame);
	}

	public static void switchToDefaultFrame() {
		getDriver().switchTo().defaultContent();
	}

	public static boolean isDisplayed(WebElement element) {
		try {
			getwait().until(ExpectedConditions.visibilityOf(element));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isEnabled(WebElement element) {
		try {
			return element.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isSelected(WebElement element) {
		try {
			return element.isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	public static void Elementclick(WebElement element) {
		try {
			getwait().until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			throw new RuntimeException("Failed to click element", e);
		}
	}

	public static void Elementclear(WebElement element) {
		try {
			element.clear();
		} catch (Exception e) {
			throw new RuntimeException("Failed to clear element", e);
		}
	}

	public static void Elementsubmit(WebElement element) {
		try {
			element.submit();
		} catch (Exception e) {
			throw new RuntimeException("Failed to submit element", e);
		}
	}

	public static String getText(WebElement element) {
		try {
			return element.getText();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get text", e);
		}
	}

	public static String getTagName(WebElement element) {
		try {
			return element.getTagName();
		} catch (Exception e) {
			throw new RuntimeException("Failed to get tag name", e);
		}
	}

	public static String getCurrentUrl() {
		return getDriver().getCurrentUrl();
	}

	public static String getTitle() {
		return getDriver().getTitle();
	}

	public static String getWindowHandle() {
		return getDriver().getWindowHandle();
	}

	// ✅ Screenshots
	public static String takeFullScreenshot() {

		try {

			final String SCREENSHOT_FOLDER = "./screenshots/";


			if (!(getDriver() == null)) {
				throw new RuntimeException("Driver does not support screenshots");
			}

			// Create folder path create and to check if not exists
			File folder = new File(SCREENSHOT_FOLDER);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			// give filename and date,current time,image format.
			String fileName = "screenshot_" + System.currentTimeMillis() + ".png";

			File take = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

			File give = new File(folder + fileName);

			FileUtils.copyFile(take, give);

			return give.getAbsolutePath();

		} catch (Exception e) {
			throw new RuntimeException("Failed to take screenshot at path: ", e);
		}
	}

	public static String takeElementScreenshot(WebElement element) {

		try {
			// folder location
			final String SCREENSHOT_FOLDER = "./screenshots/";

			// Create folder path create and to check if not exists
			File folder = new File(SCREENSHOT_FOLDER);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			// give filename and date,current time,image format for the automatic file name
			String fileName = "element_" + System.currentTimeMillis() + ".png";

			// Wait for element visibility
			getwait().until(ExpectedConditions.visibilityOf(element));

			File take = element.getScreenshotAs(OutputType.FILE);
			File give = new File(SCREENSHOT_FOLDER + fileName);
			FileUtils.copyFile(take, give);
			return give.getAbsolutePath();
		} catch (Exception e) {
			throw new RuntimeException("Failed to Element takeScreenshot ", e);
		}

	}

	public static void scroll_Screen(WebElement element) {

		JavascriptExecutor scr = (JavascriptExecutor) getDriver();

		scr.executeScript("arguments[0].scrollIntoView();", element);

	}

	public static void getAllWindows(int tabIndex) {

		Set<String> windowHandles = getDriver().getWindowHandles();

		ArrayList<String> windowList = new ArrayList<>(windowHandles);

		getDriver().switchTo().window(windowList.get(tabIndex));

	}

	// ✅ Utility
	public static void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000L); // always use long literal
		} catch (Exception e) {
			Thread.currentThread().interrupt(); // restore interrupted state
		}
	}

	// Highlight element
	public static void Elementhighlight(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].style.border='3px solid red'", element);
		} catch (Exception e) {

		}

	}

	// Returns true if file is found, false otherwise
	public static boolean isFileDownloaded(String downloadLocation, String fileName) {
		try {
			File folder = new File(downloadLocation);
			File[] allFiles = folder.listFiles();

			if (allFiles != null) {
				for (File file : allFiles) {
					if (file.getName().equals(fileName)) {
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void FileUpload(WebElement element, String filepath) {

		element.sendKeys(filepath);

	}

	public static void multipleFilesuploades(WebElement element, String filePaths) {
		try {
			String joinedFiles = String.join("\n", filePaths); // supports multiple direct path
			element.sendKeys(joinedFiles);
		} catch (Exception e) {
		}

		/*
		 * // Upload multiple files (if the input allows multiple selection)
		 * Filesupload(fileInput, "C:\\Users\\DELL\\Downloads\\dinu.jpg",
		 * "C:\\Users\\DELL\\Downloads\\test.txt" );
		 */
	}

	public static void NewTab() {
		// Open a new tab
		getDriver().switchTo().newWindow(WindowType.TAB);

	}

	// public static void Movetoslidervalue(WebElement element, targetValue) {

	/*
	 * double step = 0.5; double current =
	 * Double.parseDouble(element.getAttribute("value")); int steps = (int)
	 * Math.round((targetValue - current) / step);
	 * 
	 * element.click();
	 * 
	 * for (int i = 0; i < Math.abs(steps); i++) { if (steps > 0)
	 * element.sendKeys(Keys.ARROW_RIGHT); else element.sendKeys(Keys.ARROW_LEFT); }
	 * 
	 * //System.out.println("✅ Slider moved to: " + element.getAttribute("value"));
	 */
	// }

	public static void Movetoslidervalue(WebElement element, int startpoint, int endpoint) {

		Actions actions = new Actions(getDriver());
		actions.clickAndHold(element).pause(Duration.ofSeconds(3)).moveByOffset(startpoint, endpoint).release()
				.perform();
	}

	public static void moveSliderJS(WebElement slider, double targetValue) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].value=arguments[1];" + "arguments[0].dispatchEvent(new Event('input'));"
					+ "arguments[0].dispatchEvent(new Event('change'));", slider, targetValue);
			System.out.println("✅ Slider moved via JavaScript → " + targetValue);
		} catch (Exception e) {
			System.err.println("❌ JS fallback failed: " + e.getMessage());
		}
	}

	public static void setPriceSliderValue(WebElement slider, int priceValue) {
		try {
			int sliderValue = Math.round(priceValue / 1000f);
			if (sliderValue < 0)
				sliderValue = 0;
			if (sliderValue > 130)
				sliderValue = 130;

			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].value=arguments[1];" + "arguments[0].dispatchEvent(new Event('input'));"
					+ "arguments[0].dispatchEvent(new Event('change'));", slider, sliderValue);

			Thread.sleep(500);

			System.out.println("🎯 Target: ₹" + priceValue + " | Displayed: " + slider.getAttribute("aria-valuetext"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
