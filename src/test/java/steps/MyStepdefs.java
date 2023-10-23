package steps;

import com.google.inject.Inject;
import helper.ConfigHelper;
import helper.CsvHelper;
import helper.ScenarioContext;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.CreateUser;
import model.ExistingUser;
import org.junit.jupiter.api.Assertions;
import service.GoRest;

import java.util.List;

public class MyStepdefs {

    private final ScenarioContext scenarioContext;
    private final GoRest goRest;
    private final CsvHelper csvHelper;
    private final ConfigHelper configHelper;

    @Inject
    public MyStepdefs(GoRest goRest, ConfigHelper configHelper, CsvHelper csvHelper) {
        this.scenarioContext = ScenarioContext.getInstance();
        this.configHelper = configHelper;
        this.configHelper.init();
        String gorestUrl = configHelper.getProperties().getProperty("gorest.url");
        this.goRest = goRest;
        this.goRest.setUrl(gorestUrl);
        this.csvHelper = csvHelper;
    }

    @Given("get all users")
    public void getAllUsers() throws JsonProcessingException {
        List<ExistingUser> allExistingUsers = goRest.getAllUsers();
        scenarioContext.setContext("users", allExistingUsers);
    }

    @Then("users not empty")
    public void usersNotEmpty() {
        Assertions.assertNotNull(scenarioContext.getContext("users"));
    }

    @When("post user")
    public void postUser() throws JsonProcessingException {
        List<CreateUser> createUsers = csvHelper.readUserCsv();

        CreateUser user = createUsers.get(0);

        ExistingUser existingUser = goRest.postUser(user);
        scenarioContext.setContext("existingUser", existingUser);
        scenarioContext.setContext("user", user);
    }

    @Then("user is created")
    public void userIsCreated() {
        ExistingUser existingUser = (ExistingUser) scenarioContext.getContext("existingUser");
        CreateUser user = (CreateUser) scenarioContext.getContext("user");
        Assertions.assertNotNull(existingUser.getId());
        Assertions.assertEquals(user.getName(), existingUser.getName(), "Username is not correct!");
        Assertions.assertEquals(user.getGender(), existingUser.getGender(), "Gender is not correct!");
        Assertions.assertEquals(user.getEmail(), existingUser.getEmail(), "Email is not correct!");
        Assertions.assertEquals(user.getStatus(), existingUser.getStatus(), "Status is not correct!");
    }

    @When("put user")
    public void putUser() throws JsonProcessingException {
        ExistingUser existingUser = (ExistingUser) scenarioContext.getContext("existingUser");
        existingUser.setStatus("inactive");
        ExistingUser updatedExistingUser = goRest.putUser(existingUser);
        scenarioContext.setContext("existingUser", existingUser);
        scenarioContext.setContext("updatedExistingUser", updatedExistingUser);
    }

    @Then("user data is updated")
    public void userDataIsUpdated() {
        ExistingUser existingUser = (ExistingUser) scenarioContext.getContext("existingUser");
        ExistingUser updatedExistingUser = (ExistingUser) scenarioContext.getContext("updatedExistingUser");

        Assertions.assertEquals(existingUser.getId(), updatedExistingUser.getId(), "ID is not correct!");
        Assertions.assertEquals(existingUser.getName(), updatedExistingUser.getName(), "Username is not correct!");
        Assertions.assertEquals(existingUser.getGender(), updatedExistingUser.getGender(), "Gender is not correct!");
        Assertions.assertEquals(existingUser.getEmail(), updatedExistingUser.getEmail(), "Email is not correct!");
        Assertions.assertEquals(existingUser.getStatus(), updatedExistingUser.getStatus(), "Status is not correct!");
    }

    @When("delete user")
    public void deleteUser() throws JsonProcessingException {
        ExistingUser existingUser = (ExistingUser) scenarioContext.getContext("existingUser");
        goRest.deleteUser(existingUser);

    }

    @Then("user does not exists")
    public void userDoesNotExists() {
        ExistingUser existingUser = (ExistingUser) scenarioContext.getContext("existingUser");
        List<ExistingUser> users = (List<ExistingUser>) scenarioContext.getContext("users");
        long count = users.stream().filter(u -> u.getId().equals(existingUser.getId())).count();
        Assertions.assertEquals(0, count, "User is not deleted!");
    }

    @Given("debug")
    public void debug() {
        System.out.format("Thread ID - %2d\n", Thread.currentThread().threadId());
    }
}
