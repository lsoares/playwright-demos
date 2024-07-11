import com.microsoft.playwright.Page
import com.microsoft.playwright.junit.UsePlaywright
import pages.Homepage
import kotlin.test.Test

@UsePlaywright  
class TestSendInquiries {

    @Test
    fun `send inquiry about secure exchange`(page: Page) {
        val homepage = Homepage
            .navigate(page)
            .`accept cookies`()
            .`close privacy policy`()
        homepage
            .`pick SecureExchange solution`()
            .`go to inquiry form`()
            .`submit inquiry form`(
                firstName = "User",
                lastName = "Test",
                email = "test@abc.com",
                phoneNumber = "+351915332242",
                message = "I want to know more about..",
            )
    }
}