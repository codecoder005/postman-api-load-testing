package com.orgofarmsgroup.controller;


import com.orgofarmsgroup.util.AppConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGet200Response() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("200"))
                .andExpect(jsonPath("$.data.uid").value(101L))
                .andExpect(jsonPath("$.data.name").value("John"))
                .andExpect(jsonPath("$.data.email").value("john@email.com"));
    }

    @DisplayName("Invalid name passing to search api")
    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "1","12","123", " ", ".", ",", "$#@", "abcdefghijabcdefghija"})
    void testUsersSearchApiInvalidName(String name) throws Exception {
        String apiRequestBodyContent = "";
        mockMvc.perform(
                get(AppConstants.API_URI.USERS_API_URI.USERS_SEARCH)
                        .param("name",name)
        ).andDo(print())
        .andExpect(status().isBadRequest());
    }

    @DisplayName("Invalid count passing to search api")
    @ParameterizedTest
    @ValueSource(longs = {-1L, 0L, 10001L, 98765L})
    void testUsersSearchApiInvalidCount(Long count) throws Exception {
        mockMvc.perform(
                get(AppConstants.API_URI.USERS_API_URI.USERS_SEARCH)
                        .param("count", count.toString())
        ).andDo(print())
        .andExpect(status().isBadRequest());
    }

    @DisplayName("Invalid sortBy passing to search api")
    @ParameterizedTest
    @ValueSource(strings = {"ascending", "descending", "a", "d"})
    void testUsersSearchApiInvalidSortByOrder(String sortBy) throws Exception {
        mockMvc.perform(
                get(AppConstants.API_URI.USERS_API_URI.USERS_SEARCH)
                        .param("sortBy", sortBy)
        ).andDo(print())
        .andExpect(status().isBadRequest());
    }
}