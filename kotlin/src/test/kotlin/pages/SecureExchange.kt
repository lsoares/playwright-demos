package pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.Page.GetByRoleOptions
import com.microsoft.playwright.options.AriaRole.LINK
import components.InquiryForm

class SecureExchange(private val page: Page) {

    fun `go to inquiry form`(): SecureExchange {
        page.getByRole(LINK, GetByRoleOptions().setName("Send inquiry")).click()
        return this
    }

    fun `submit inquiry form`(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        message: String
    ): SecureExchange {
        InquiryForm(page).invoke(
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            message = message,
        )
        return this
    }
}

