import { expect, Page } from "@playwright/test"
import { SecureExchange } from "./secureExchange"
import { step } from "../testStep"

export class Homepage {
  constructor(private page: Page) {}

  @step
  static async navigate(page: Page) {
    await page.goto("/")
    await expect(page).toHaveTitle(/DSwiss – We make your business processes simple and secure/i)
    return new Homepage(page)
  }

  @step
  async acceptCookies() {
    await this.page.getByRole("button", { name: "Accept" }).click()
  }

  @step
  async closePrivacyPolicy() {
    const policyIframe = this.page.frameLocator("iframe[title='Popup CTA']")
    await policyIframe.locator("css=.button-container").click()
  }

  @step
  async pickSecureExchangeSolution() {
    const nav = this.page.getByRole("navigation")
    await nav.getByRole("button", { name: "Solutions" }).hover()
    await nav.getByRole("link", { name: "SecureExchange" }).click()
    await expect(this.page).toHaveTitle("SecureExchange")
    return new SecureExchange(this.page)
  }
}
