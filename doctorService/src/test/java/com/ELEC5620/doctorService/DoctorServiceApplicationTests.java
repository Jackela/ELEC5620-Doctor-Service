package com.ELEC5620.doctorService;

import com.ELEC5620.doctorService.controller.DoctorController;
import com.ELEC5620.doctorService.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** HTTP contracts with no application bootstrap, Secrets Manager or model calls. */
class DoctorServiceApplicationTests {
    private DoctorService service;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        service = mock(DoctorService.class);
        mvc = MockMvcBuilders.standaloneSetup(new DoctorController(service)).build();
    }

    @Test
    void postDoctorPassesTheExactQuestionAndReturnsTheAnswer() throws Exception {
        String question = "A question with \"quotes\" and 中文";
        when(service.ask(question)).thenReturn("Controlled answer");
        mvc.perform(post("/doctor").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(Map.of("question", question))))
                .andExpect(status().isOk()).andExpect(content().string("Controlled answer"));
        verify(service).ask(question);
        verifyNoMoreInteractions(service);
    }

    @Test
    void malformedJsonIsRejectedBeforeCallingTheService() throws Exception {
        mvc.perform(post("/doctor").contentType(MediaType.APPLICATION_JSON).content("{broken"))
                .andExpect(status().isBadRequest());
        verifyNoInteractions(service);
    }

    @Test
    void missingBodyIsRejectedBeforeCallingTheService() throws Exception {
        mvc.perform(post("/doctor").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verifyNoInteractions(service);
    }
}
