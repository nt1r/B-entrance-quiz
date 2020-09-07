package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.dto.AddNewMemberRequestDto;
import com.thoughtworks.capability.gtb.entrancequiz.service.GroupAssignService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GroupAssignControllerTest {
    private final String getAllMembersUrl = "/group-api/init-list";
    private final String addOneMemberUrl = "/group-api/member";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    GroupAssignService groupAssignService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        groupAssignService = new GroupAssignService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void shouldGetAllMembersSuccess() throws Exception {
        mockMvc.perform(get(getAllMembersUrl).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("$.memberList", hasSize(35)))
                .andExpect(jsonPath("$.memberList[0].name", is("沈乐棋")))
                .andExpect(jsonPath("$.memberList[4].name", is("王登宇")))
                .andExpect(jsonPath("$.memberList[7].name", is("廖燊")))
                .andExpect(jsonPath("$.memberList[11].name", is("肖美琦")))
                .andExpect(jsonPath("$.memberList[13].name", is("齐瑾浩")))
                .andExpect(jsonPath("$.memberList[19].name", is("党泽")))
                .andExpect(jsonPath("$.memberList[23].name", is("马庆")))
                .andExpect(jsonPath("$.memberList[29].name", is("赵允齐")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddNewMemberSuccess() throws Exception {
        AddNewMemberRequestDto addNewMemberRequestDto = AddNewMemberRequestDto.builder().name("张三").build();

        mockMvc.perform(post(addOneMemberUrl).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addNewMemberRequestDto)))
                .andExpect(jsonPath("$.memberList", hasSize(36)))
                .andExpect(jsonPath("$.memberList[0].name", is("沈乐棋")))
                .andExpect(jsonPath("$.memberList[4].name", is("王登宇")))
                .andExpect(jsonPath("$.memberList[7].name", is("廖燊")))
                .andExpect(jsonPath("$.memberList[11].name", is("肖美琦")))
                .andExpect(jsonPath("$.memberList[13].name", is("齐瑾浩")))
                .andExpect(jsonPath("$.memberList[19].name", is("党泽")))
                .andExpect(jsonPath("$.memberList[23].name", is("马庆")))
                .andExpect(jsonPath("$.memberList[29].name", is("赵允齐")))
                .andExpect(jsonPath("$.memberList[35].name", is("张三")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddNewMemberFailedWhenNameIsEmpty() throws Exception {
        AddNewMemberRequestDto addNewMemberRequestDto = AddNewMemberRequestDto.builder().name("").build();

        mockMvc.perform(post(addOneMemberUrl).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addNewMemberRequestDto)))
                .andExpect(jsonPath("$.memberList", hasSize(35)))
                .andExpect(jsonPath("$.memberList[0].name", is("沈乐棋")))
                .andExpect(jsonPath("$.memberList[4].name", is("王登宇")))
                .andExpect(jsonPath("$.memberList[7].name", is("廖燊")))
                .andExpect(jsonPath("$.memberList[11].name", is("肖美琦")))
                .andExpect(jsonPath("$.memberList[13].name", is("齐瑾浩")))
                .andExpect(jsonPath("$.memberList[19].name", is("党泽")))
                .andExpect(jsonPath("$.memberList[23].name", is("马庆")))
                .andExpect(jsonPath("$.memberList[29].name", is("赵允齐")))
                .andExpect(status().isOk());
    }
}