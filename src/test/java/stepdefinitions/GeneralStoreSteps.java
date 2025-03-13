package stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import screens.CartScreen;
import screens.HomeScreen;
import screens.ProductsScreen;

public class GeneralStoreSteps {

    private AppiumDriver appiumDriver;
    private HomeScreen homePage;
    private ProductsScreen productsPage;
    private CartScreen cartPage;

    @Given("I open the General Store app")
    public void i_open_the_general_store_app() {
        appiumDriver = Hooks.getAppiumDriver();
        homePage = new HomeScreen(appiumDriver);
    }

    @When("I select {string} from the country dropdown")
    public void i_select_country_from_the_dropdown(String country) {
        homePage.selectCountry(country);
    }

    @When("I enter my name as {string}")
    public void i_enter_my_name_as(String name) {
        homePage.enterName(name);
    }

    @When("I select my gender as {string}")
    public void i_select_my_gender_as(String gender) {
        homePage.selectGender(gender);
    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String button) {
        homePage.clickLetsShop();
        productsPage = new ProductsScreen(appiumDriver);
    }

    @When("I add two products to the cart")
    public void i_add_two_products_to_the_cart() {
        productsPage.addProductsToCart();
    }

    @When("I navigate to the cart screen")
    public void i_navigate_to_the_cart_screen() {
        productsPage.navigateToCart();
        cartPage = new CartScreen(appiumDriver);
    }

    @Then("I should see the products in the cart")
    public void i_should_see_the_products_in_the_cart() {
        Assertions.assertThat(cartPage.areProductsDisplayed()).isTrue();
    }

    @Then("the total amount should equal the sum of the two products' prices")
    public void the_total_amount_should_equal_the_sum_of_the_two_products_prices() {
        double totalAmount = cartPage.getTotalAmount();
        double sumOfPrices = cartPage.getSumOfProductPrices();
        Assertions.assertThat(totalAmount).isEqualTo(sumOfPrices);
    }
}