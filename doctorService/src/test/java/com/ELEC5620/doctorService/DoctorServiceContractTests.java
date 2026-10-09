package com.ELEC5620.doctorService;

import com.ELEC5620.doctorService.service.DoctorServiceImpl;
import com.ELEC5620.doctorService.tools.ApiTools;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/** Exercise real AI service orchestration with a controlled model boundary. */
class DoctorServiceContractTests {
    @Test
    void askPreservesTheUserQuestionAndReturnsModelText() {
        OpenAiChatModel model = mock(OpenAiChatModel.class);
        when(model.generate(anyList(), anyList())).thenReturn(Response.from(AiMessage.from("Fixture reply")));
        var service = new DoctorServiceImpl(model, mock(ApiTools.class));
        assertEquals("Fixture reply", service.ask("Original 中文 question"));
        ArgumentCaptor<List<ChatMessage>> messages = ArgumentCaptor.forClass(List.class);
        verify(model).generate(messages.capture(), anyList());
        assertEquals("Original 中文 question", ((UserMessage) messages.getValue().get(0)).singleText());
    }

    @Test
    void modelFailureRemainsAFailure() {
        OpenAiChatModel model = mock(OpenAiChatModel.class);
        when(model.generate(anyList(), anyList())).thenThrow(new IllegalStateException("controlled unavailable"));
        var service = new DoctorServiceImpl(model, mock(ApiTools.class));
        var failure = assertThrows(IllegalStateException.class, () -> service.ask("Question"));
        assertEquals("controlled unavailable", failure.getMessage());
    }
}
