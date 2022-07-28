package com.oardic.resilience4jdemo;

import com.oardic.resilience4jdemo.controller.SampleController;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link SampleController}.
 *
 * @author Okan ARDIC
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@RepeatedTest(4)
	public void whenGetBackendAThenFirstCall200AndThreeCalls429(RepetitionInfo repetitionInfo) throws Exception {
		ResultMatcher result = repetitionInfo.getCurrentRepetition() == 1 ? status().isOk() :
			status().isTooManyRequests();
		mockMvc.perform(MockMvcRequestBuilders.get("/messageA"))
		       .andExpect(result);
	}

	@RepeatedTest(4)
	public void whenGetBackendBThenTwoCalls200AndTwoCalls429(RepetitionInfo repetitionInfo) throws Exception {
		ResultMatcher result = repetitionInfo.getCurrentRepetition() <= 2 ? status().isOk() :
			status().isTooManyRequests();
		mockMvc.perform(MockMvcRequestBuilders.get("/messageB"))
		       .andExpect(result);
	}

	@RepeatedTest(4)
	public void whenGetBackendCThenThreeCalls200AndOneCall429(RepetitionInfo repetitionInfo) throws Exception {
		ResultMatcher result = repetitionInfo.getCurrentRepetition() <= 3 ? status().isOk() :
			status().isTooManyRequests();
		mockMvc.perform(MockMvcRequestBuilders.get("/messageC"))
		       .andExpect(result);
	}
}
