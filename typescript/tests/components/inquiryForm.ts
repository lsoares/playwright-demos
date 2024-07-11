import { Page } from "@playwright/test"

export class InquiryForm {
  constructor(private page: Page) {}

  async fillInquiryForm(inquiryData: {
    firstName: string
    lastName: string
    email: string
    phoneNumber: string
    message: string
  }) {
    await this.page.getByRole("textbox", { name: /first name/i }).fill(inquiryData.firstName)
    await this.page.getByRole("textbox", { name: /last name/i }).fill(inquiryData.lastName)
    await this.page.getByRole("textbox", { name: /email/i }).fill(inquiryData.email)
    await this.page.getByRole("textbox", { name: /phone number/i }).fill(inquiryData.phoneNumber)
    await this.page.getByRole("textbox", { name: /message/i }).fill(inquiryData.message)
    await this.page.getByRole("checkbox", { name: /i agree to receive marketing communications from DSwiss/i }).click()
    await this.page
      .getByRole("checkbox", {
        name: /I agree to allow DSwiss to store and process my personal data/i,
      })
      .click()
    await this.page.getByRole("button", { name: "submit" }).click()
  }
}
