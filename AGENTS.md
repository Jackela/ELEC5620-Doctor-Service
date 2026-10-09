# Maintenance entry

Read README.md, doctorService/build.gradle and the source affected by the change. The controller, DoctorService interface/implementation, ApiTools and configuration are the authority for current behavior. This is a course microservice; preserve its existing authorship, MIT text and product version.

Use the checked-in wrapper from doctorService/: ./gradlew check buildZip. CI runs JDK 17. Keep controller, service and outbound HTTP contracts executable with controlled boundaries. Tests must not bootstrap Secrets Manager or call AWS/model endpoints. Do not replace real assertions with empty context tests or skip failed checks.

For critical behavior, reproduce the failure before fixing it. Keep changes minimal; existing runtime endpoints and response semantics must remain compatible. Runtime configuration and manual deployment are described in README.md and .github/workflows/deploy-function.yml. A check or ZIP is not deployment evidence; normal push/PR work does not authorize a live deployment. Report base/head commits, checks and concrete unverified environments.
