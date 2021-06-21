Feature: Get Market data from Yahoo for a given date
  This covers tests to login to Yahoo.com, navigate to Market data page and get data for a given date

  @GetMarketData
  Scenario: Get Market Data
    Given I login with a valid user
    Then I navigate to Market Data Calendars page and verify the title has "Financial calendars"
    And I get market data for "2021-06-18"
    And I close browser
