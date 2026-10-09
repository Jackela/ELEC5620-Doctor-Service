package com.ELEC5620.doctorService;

import com.ELEC5620.doctorService.tools.ApiTools;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

/** Assert outbound tool contracts with an in-process HTTP interceptor. */
class ApiToolsContractTests {
    private static final String API = "https://yhrj2rgi0c.execute-api.ap-southeast-2.amazonaws.com/prod/";

    @Test
    void healthAdviceSerializesQuotesNewlinesAndUnicode() throws Exception {
        RestTemplate http = new RestTemplate();
        var server = MockRestServiceServer.bindTo(http).build();
        String input = "Line \"one\"\n中文";
        server.expect(requestTo(API + "health-advice")).andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(Map.of("userInput", input))))
                .andRespond(withSuccess("controlled health reply", MediaType.TEXT_PLAIN));
        assertEquals("controlled health reply", new ApiTools(http).callHealthAdviceApi(input));
        server.verify();
    }

    @Test
    void translatePreservesAllFieldsAsJson() throws Exception {
        RestTemplate http = new RestTemplate();
        var server = MockRestServiceServer.bindTo(http).build();
        String input = "Translate \"this\"\n中文";
        server.expect(requestTo(API + "translate")).andExpect(method(HttpMethod.POST))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(Map.of(
                        "text", input, "sourceLanguage", "zh", "targetLanguage", "en"))))
                .andRespond(withSuccess("controlled translation", MediaType.TEXT_PLAIN));
        assertEquals("controlled translation", new ApiTools(http).callTranslateApi(input, "zh", "en"));
        server.verify();
    }

    @Test
    void patientAndRegionalToolsKeepTheirExistingPostContracts() {
        RestTemplate http = new RestTemplate();
        var server = MockRestServiceServer.bindTo(http).build();
        server.expect(requestTo(API + "patient-summary/test-user")).andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("summary", MediaType.TEXT_PLAIN));
        server.expect(requestTo(API + "regional-report/")).andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess("report", MediaType.TEXT_PLAIN));
        var tools = new ApiTools(http);
        assertEquals("summary", tools.callPatientSummaryApi("test-user"));
        assertEquals("report", tools.callRegionalReportApi());
        server.verify();
    }
}
