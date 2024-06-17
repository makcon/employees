### Tech stack

- Kotlin
- Maven
- Spring boot
- MongoDB (embedded)

### Architecture

- Monolith with maven modules
  - `employees` manages employees CRUD operations. Later it can be converted to a separate service
    - It based on hexagonal architecture.
      - `domain` package is the center part and responsible for the business logic
      - `adapter` is the right part that communicates with DB
      - `client` is the left part, it receives requests
    - All communications with the module go via `EmployeesClient` interface. Currently there is internal implementation
      because it's a module of the same artifact. After moving to a separate service need to implement an
      http/websockets client
    - Document versioning pattern to prevent concurrent update
    - For each modification a related event is created and stored in DB in the same transaction. This way we solve
      atomic issue between storing an object and sending an event. An event's structure is simple for this exercise,
      just contains an object id
  - `app` is the application runner. It also has all needed configuration and execute acceptance (integration) tests for
    the whole app
  - `admin-api` exposes API endpoints for an admin backoffice
  - `commons` has common logic/objects. It should be split and stored in artifactory storage e.q. Nexus
  - `messaging` a library contains common logic for sending/receiving events. It has a runner that constantly
    reads `events` collection and send events (for this example locally). Should be in Nexus as well
- Testing
  - Mother pattern to create valid random objects
  - Acceptance testing to test end-to-end (in scope of the service) logic
  - Unit testing using Junit 5 and Kotest lib for assertion