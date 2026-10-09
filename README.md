# Doctor Service (ELEC5620 Microservices)

This course service accepts a question at `POST /doctor`, forwards it through a LangChain4j service, and returns model text. The model can invoke HTTP tools for translation, health advice, patient summaries and regional reports. The code is in `doctorService/`; the repository root is not a Gradle project.

## Build and verify

Use JDK 17 (the CI runtime) and the checked-in Gradle wrapper. No AWS credentials or model key are required for these checks:

```sh
cd doctorService
./gradlew check buildZip
```

Controller tests use standalone MockMvc; service tests control the model boundary; tool tests intercept HTTP in process. They check successful replies, malformed requests, model failures and JSON escaping. The Lambda ZIP is `build/distributions/doctorService-0.0.1-SNAPSHOT.zip`, and packaging requires the checks to pass. Push and pull request CI run the same command.

## Runtime configuration

`./gradlew bootRun` starts the service on port 8080. Unlike the offline checks, application startup reads `OPENAI_API_KEY` from the AWS Secrets Manager secret declared in `SecretsManagerUtil`, in `ap-southeast-2`. A working runtime therefore needs authorized AWS access and that secret. Ordinary requests can call a paid model and the external API Gateway endpoints in `ApiTools`; the test suite does neither.

```http
POST /doctor
Content-Type: application/json

{"question":"Your question"}
```

This repository contains AWS SDK dependencies and a Lambda handler. It has no implemented doctor database repository or demonstrated DynamoDB persistence. The runtime wiring and tests establish code behavior; they do not establish a live deployment or clinical validation. Course authorship and license are retained below; this repository alone does not substantiate project management roles or contributions to other services.

## Deployment

The manual **Deploy Doctor Function to AWS Lambda** workflow first verifies and packages the service. Deployment runs only when its explicit `deploy` input is enabled, using the configured AWS credentials and execution role. Push and pull request checks do not deploy. Consult the workflow and `AwsLambdaHandler` for the current function and handler settings.

## Maintenance

Read [AGENTS.md](AGENTS.md) for the source and validation entry points. Keep external services controlled at test boundaries and report local checks separately from AWS runtime evidence.

## 📄 License

This project is licensed under the MIT License.

---

**MIT License**

Copyright (c) 2024 Weixuan Kong

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.