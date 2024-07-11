import { Page } from "@playwright/test"
import { InquiryForm } from "../components/inquiryForm.ts"
import { step } from "../testStep.ts"

export class SecureExchange {
  inquiryForm: InquiryForm

  constructor(private page: Page) {
    this.inquiryForm = new InquiryForm(this.page)
  }

  @step
  async goToInquiryForm() {
    await this.page.getByRole("link", { name: "Send inquiry" }).click()
  }

  @step
  async fillInquiryForm(inquiryData: {
    firstName: string
    lastName: string
    email: string
    phoneNumber: string
    message: string
  }) {
    await this.inquiryForm.fillInquiryForm(inquiryData)
  }
}
