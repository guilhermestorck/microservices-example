# Catalog Service

Use this service to query our catalog or get the information of a product given an id

### Features included
- **Code with Kotlin**  
Kotlin is the default language for this project
- **Multi module build**  
App, core and contract have separate build, test and publishing lifecycles
- **Publishing to maven repo**  
A local folder is configured as a maven repository, but that can be replaced by a Nexus server, for instance
- **Testing with Spock**  
Spock is configured as the default test framework. Coverage is being provided by JaCoCo
- **Service cooperation**  
A service can import the contract module of another to more easily implement a client


### To do
- **Containerization**  
Fine tune the docker file
- **Service discovery**  
Add Consul integration
- **Dev environment**  
Add Mock Server to mock dependencies for the dev env
- **Service orchestration**  
Add Kubernetes goodies
- **Component testing**  
Add Gherkin support
- **Feature toggle support**  
Integrate with FF4j