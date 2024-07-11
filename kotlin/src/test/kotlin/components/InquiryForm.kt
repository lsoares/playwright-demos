package components

import com.microsoft.playwright.Page
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.options.AriaRole.*
import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE

class InquiryForm(private val page: Page) {

    fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        message: String
    ) {
        page.getByRole(TEXTBOX, GetByRoleOptions().setName(Pattern.compile("first name", CASE_INSENSITIVE)))
            .fill(firstName)
        page.getByRole(TEXTBOX, GetByRoleOptions().setName(Pattern.compile("last name", CASE_INSENSITIVE)))
            .fill(lastName)
        page.getByRole(TEXTBOX, GetByRoleOptions().setName(Pattern.compile("email", CASE_INSENSITIVE)))
            .fill(email)
        page.getByRole(TEXTBOX, GetByRoleOptions().setName(Pattern.compile("phone number", CASE_INSENSITIVE)))
            .fill(phoneNumber)
        page.getByRole(TEXTBOX, GetByRoleOptions().setName(Pattern.compile("message", CASE_INSENSITIVE)))
            .fill(message)
        page.getByRole(
            CHECKBOX,
            GetByRoleOptions().setName(
                Pattern.compile("i agree to receive marketing communications from DSwiss", CASE_INSENSITIVE)
            )
        ).click()
        page.getByRole(
            CHECKBOX,
            GetByRoleOptions().setName(
                Pattern.compile("I agree to allow DSwiss to store and process my personal data", CASE_INSENSITIVE)
            )
        ).click()
        page.getByRole(BUTTON, GetByRoleOptions().setName("submit")).click()
    }
}