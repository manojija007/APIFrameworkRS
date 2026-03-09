Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<Language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name     | Language | address  |
      | liyanshi | Marathi  | Tanjavur |

  @DeletePlace
  Scenario: Verify if Delete place functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
