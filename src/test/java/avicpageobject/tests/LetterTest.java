package avicpageobject.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LetterTest extends BaseTest {

    private static final String NAME = "any"; // entered data for name field
    private static final String MESSAGE = "any"; // entered data for message field
    private static final String[] ARRAY_OF_EMAILS = {"1any", "2any@", "3any@1"}; // array of entered data for mail field
    boolean IS_VALID_FIELD = false; // variable to check error

    @Test
    public void checkMailForm(){
        getHomePage().clickOnLetterButton();
        getLetterPage().chooseDepartment();
        getLetterPage().fillTheNameField(NAME);
        getLetterPage().fillTheMessageField(MESSAGE);

        for ( String email: ARRAY_OF_EMAILS) {
            getLetterPage().fillTheMailField(email); // select previous text and paste the new one to the mailField
            getLetterPage().clickOnSubmitButton(); // click submit button
            getLetterPage().waitVisibilityOfElement(30, getLetterPage().showErrorNotation());

            // checking the availability of error notation
            if (getLetterPage().showErrorNotation() != null) {
                IS_VALID_FIELD = false; // error notation was created
            } else {
                IS_VALID_FIELD = true; // error notation was not created
            }
        }

        assertEquals(IS_VALID_FIELD, false);
    }
}
