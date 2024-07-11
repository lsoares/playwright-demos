package pages

import com.microsoft.playwright.Locator.GetByRoleOptions
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import com.microsoft.playwright.options.AriaRole.*
import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE

class Homepage(private val page: Page) {
    fun `accept cookies`(): Homepage {
        page.getByRole(BUTTON, Page.GetByRoleOptions().setName("Accept")).click()
        return this
    }

    fun `close privacy policy`(): Homepage {
        val policyIframe = page.frameLocator("iframe[title='Popup CTA']")
        policyIframe.locator("css=.button-container").click()
        return this
    }

    fun `pick SecureExchange solution`(): SecureExchange {
        val nav = page.getByRole(NAVIGATION)
        nav.getByRole(BUTTON, GetByRoleOptions().setName("Solutions")).hover()
        nav.getByRole(LINK, GetByRoleOptions().setName("SecureExchange")).click()
        assertThat(page).hasTitle("SecureExchange")
        return SecureExchange(page)
    }

    companion object {
        fun navigate(page: Page): Homepage {
            page.navigate("https://dswiss.com/en")
            assertThat(page).hasTitle(
                Pattern.compile(
                    "DSwiss – We make your business processes simple and secure",
                    CASE_INSENSITIVE
                )
            );
            return Homepage(page)
        }
    }
}

