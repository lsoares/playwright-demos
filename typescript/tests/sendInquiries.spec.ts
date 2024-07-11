import { test } from "@playwright/test"
import { Homepage } from "./pages/homepage"

test("send inquiry about secure exchange", async ({ page }) => {
  const homepage = await Homepage.navigate(page)
  await homepage.acceptCookies()
  await homepage.closePrivacyPolicy()
  const secureExchange = await homepage.pickSecureExchangeSolution()
  await secureExchange.goToInquiryForm()
  await secureExchange.fillInquiryForm({
    firstName: "User",
    lastName: "Test",
    email: "test@abc.com",
    phoneNumber: "+351915332242",
    message: "I want to know more about..",
  })
})
