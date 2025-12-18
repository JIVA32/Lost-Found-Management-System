# Lost-Found-Management-System
Centralized Lost &amp; Found Management System (CLFMS)
## Design Pattern Used
This project uses the Repository Design Pattern to separate business logic from data access logic. 
This approach improves maintainability and allows easy replacement of the storage mechanism.



## Dockerization
The application is dockerized using a Dockerfile. 
Docker packages the Java runtime, application JAR, and JSON data files to ensure consistent execution across environments.



## Testing Plan

### Testing Strategy
The system was tested using manual and functional testing techniques to verify that all core features behave as expected. Each module was tested independently before full system execution.

### Testing Environment
- Operating System: Windows 10
- Programming Language: Java 21
- IDE: Visual Studio Code
- Build Tool: Maven
- Data Storage: JSON files

### Test Types Used
- Unit Testing (manual verification of methods)
- Functional Testing (testing user workflows)
- Input Validation Testing


### Test Cases

| Test Case ID | Feature Tested | Input                | Expected Result          | Status |
|------------- |-------------- -|-------               |-----------------         |--------|
| TC-01        | Add Lost Item  | Item details entered | Item saved to items.json | Pass   |
| TC-02        | View Items     | Request item list    | Items displayed correctly| Pass   |
| TC-03        | Submit Claim   | Valid item ID        | Claim saved as Pending   | Pass   |
| TC-04        | Approve Claim  | Admin approval       | Claim status updated     | Pass   |
| TC-05        | Invalid Input  | Empty item name      | Error message shown      | Pass   |

“I prepared a testing plan defining environment, test strategy, and test cases. I validated each feature manually by executing the system and verifying outputs against expected results.”
