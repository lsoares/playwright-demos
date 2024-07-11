import re
from playwright.sync_api import Page, expect

def test_send_inquiry_about_secure_exchange(page: Page):
    homepage = Homepage\
        .navigate(page)\
        .accept_cookies()\
        .close_privacy_policy()
    homepage\
        .pick_secure_exchange_solution()\
        .go_to_inquiry_form()\
        .submit_inquiry_form(
            first_name="User",
            last_name="Test",
            email="test@abc.com",
            phone_number="+351915332242",
            message="I want to know more about..",
    )


class Homepage:
    def __init__(self, page: Page):
        self.page = page

    def accept_cookies(self) -> 'Homepage':
        self.page.get_by_role("button", name="Accept").click()
        return self

    def close_privacy_policy(self) -> 'Homepage':
        policy_iframe = self.page.frame_locator("iframe[title='Popup CTA']")
        policy_iframe.locator(".button-container").click()
        return self

    def pick_secure_exchange_solution(self) -> 'SecureExchange':
        nav = self.page.get_by_role("navigation")
        nav.get_by_role("button", name="Solutions").hover()
        nav.get_by_role("link", name="SecureExchange").click()
        expect(self.page).to_have_title("SecureExchange")
        return SecureExchange(self.page)

    @staticmethod
    def navigate(page: Page) -> 'Homepage':
        page.goto("https://dswiss.com/en")
        expect(page).to_have_title(re.compile("We make your business processes simple and secure", re.IGNORECASE))
        return Homepage(page)

class SecureExchange:
    def __init__(self, page: Page):
        self.page = page

    def go_to_inquiry_form():
        ...
    
    def submit_inquiry_form():
        ...

