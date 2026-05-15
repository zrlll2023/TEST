package com.example.app.service.impl;

import com.example.app.common.BusinessException;
import com.example.app.entity.AiChat;
import com.example.app.entity.AiInterview;
import com.example.app.entity.AiResumeAnalysis;
import com.example.app.mapper.AiChatMapper;
import com.example.app.mapper.AiInterviewMapper;
import com.example.app.mapper.AiResumeAnalysisMapper;
import com.example.app.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    private final AiResumeAnalysisMapper resumeAnalysisMapper;
    private final AiInterviewMapper interviewMapper;
    private final AiChatMapper chatMapper;

    private static final List<String> INTERVIEW_QUESTIONS = Arrays.asList(
            "请介绍一下你自己",
            "你为什么选择我们公司？",
            "你做过的最有挑战性的项目是什么？",
            "请谈谈你对Java多线程的理解",
            "Spring Boot的核心注解有哪些？",
            "MyBatis和JDBC的区别是什么？",
            "MySQL的索引有哪些类型？",
            "Redis的数据结构有哪些？",
            "请描述一次你解决Bug的经历",
            "你的职业规划是什么？"
    );

    @Override
    @Transactional
    public AiResumeAnalysis analyzeResume(Long userId, String resumeContent) {
        AiResumeAnalysis analysis = new AiResumeAnalysis();
        analysis.setUserId(userId);
        analysis.setResumeContent(resumeContent);
        analysis.setFormatSuggestions(generateFormatSuggestions());
        analysis.setSkillMatching(generateSkillMatching());
        analysis.setExperiencePolish(generateExperiencePolish());
        analysis.setGapAnalysis(generateGapAnalysis());
        analysis.setOverallScore(generateOverallScore());
        analysis.setCreatedAt(LocalDateTime.now());
        resumeAnalysisMapper.insert(analysis);
        return analysis;
    }

    @Override
    @Transactional
    public AiInterview startInterview(Long userId, String targetPosition, String targetCompany) {
        AiInterview interview = new AiInterview();
        interview.setUserId(userId);
        interview.setTargetPosition(targetPosition);
        interview.setTargetCompany(targetCompany);
        interview.setStatus("IN_PROGRESS");
        interview.setScore(0);
        interview.setDuration(0);
        interview.setQuestions("[]");
        interview.setAnswers("[]");
        interview.setCreatedAt(LocalDateTime.now());
        interview.setUpdatedAt(LocalDateTime.now());
        interviewMapper.insert(interview);
        return interview;
    }

    @Override
    public Map<String, Object> getNextQuestion(Long interviewId) {
        AiInterview interview = interviewMapper.selectById(interviewId)
                .orElseThrow(() -> new BusinessException(404, "面试不存在"));

        if (!"IN_PROGRESS".equals(interview.getStatus())) {
            throw new BusinessException(400, "面试已结束");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("question", INTERVIEW_QUESTIONS.get(0));
        result.put("questionIndex", 0);
        result.put("totalQuestions", INTERVIEW_QUESTIONS.size());
        
        return result;
    }

    @Override
    @Transactional
    public Map<String, Object> submitAnswer(Long interviewId, String answer) {
        AiInterview interview = interviewMapper.selectById(interviewId)
                .orElseThrow(() -> new BusinessException(404, "面试不存在"));

        Map<String, Object> result = new HashMap<>();
        result.put("feedback", generateFeedback(answer));
        result.put("suggestion", generateSuggestion());
        
        return result;
    }

    @Override
    @Transactional
    public AiInterview finishInterview(Long interviewId) {
        AiInterview interview = interviewMapper.selectById(interviewId)
                .orElseThrow(() -> new BusinessException(404, "面试不存在"));

        interview.setStatus("COMPLETED");
        interview.setScore(generateFinalScore());
        interview.setReport(generateReport(interview));
        interview.setUpdatedAt(LocalDateTime.now());
        interviewMapper.updateById(interview);
        
        return interview;
    }

    @Override
    public List<AiInterview> getInterviewHistory(Long userId) {
        return interviewMapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public String chatWithAi(Long userId, String sessionId, String message, String chatType) {
        if (sessionId == null || sessionId.isEmpty()) {
            sessionId = UUID.randomUUID().toString();
        }

        AiChat userMessage = new AiChat();
        userMessage.setUserId(userId);
        userMessage.setSessionId(sessionId);
        userMessage.setRole("user");
        userMessage.setContent(message);
        userMessage.setChatType(chatType);
        userMessage.setCreatedAt(LocalDateTime.now());
        chatMapper.insert(userMessage);

        String aiResponse = generateAiResponse(message);
        
        AiChat aiMessage = new AiChat();
        aiMessage.setUserId(userId);
        aiMessage.setSessionId(sessionId);
        aiMessage.setRole("assistant");
        aiMessage.setContent(aiResponse);
        aiMessage.setChatType(chatType);
        aiMessage.setCreatedAt(LocalDateTime.now());
        chatMapper.insert(aiMessage);

        return aiResponse;
    }

    @Override
    public List<AiChat> getChatHistory(Long userId, String sessionId) {
        return chatMapper.findBySessionId(sessionId);
    }

    @Override
    public String reviewCode(Long userId, String code, String language) {
        return "【代码评审结果】\n\n" +
               "语言：" + language + "\n\n" +
               "代码风格：良好\n" +
               "代码结构：清晰\n" +
               "\n【优化建议】\n" +
               "1. 建议添加适当的注释说明代码意图\n" +
               "2. 考虑提取重复代码为方法\n" +
               "3. 注意异常处理的完整性\n" +
               "\n【最佳实践】\n" +
               "- 遵循单一职责原则\n" +
               "- 使用有意义的变量命名\n" +
               "- 保持方法短小精悍";
    }

    @Override
    public String getCareerAdvice(Long userId, String question) {
        return "【职业规划建议】\n\n" +
               "针对你的问题：\"" + question + "\"\n\n" +
               "建议如下：\n" +
               "1. 明确职业目标，制定短期和长期计划\n" +
               "2. 持续学习新技术，保持竞争力\n" +
               "3. 积累项目经验，建立作品集\n" +
               "4. 积极参与技术社区，扩展人脉\n" +
               "5. 定期复盘，调整职业方向\n\n" +
               "如有具体问题，欢迎继续提问！";
    }

    private String generateFormatSuggestions() {
        return "简历格式良好，建议增加以下内容：\n" +
               "- 项目经历部分添加量化成果\n" +
               "- 添加技术栈标签\n" +
               "- 补充实习经历细节";
    }

    private String generateSkillMatching() {
        return "技能匹配度：85%\n" +
               "- Java：熟练\n" +
               "- Spring Boot：熟练\n" +
               "- MySQL：良好\n" +
               "- Redis：了解";
    }

    private String generateExperiencePolish() {
        return "经历描述润色建议：\n" +
               "- 使用STAR法则描述项目经历\n" +
               "- 突出技术难点和解决方案\n" +
               "- 量化工作成果";
    }

    private String generateGapAnalysis() {
        return "与目标岗位差距分析：\n" +
               "- 建议加强分布式系统知识\n" +
               "- 增加高并发场景经验\n" +
               "- 提升系统设计能力";
    }

    private Integer generateOverallScore() {
        return new Random().nextInt(20) + 75;
    }

    private String generateFeedback(String answer) {
        return "回答评价：良好\n" +
               "- 思路清晰，表达流畅\n" +
               "- 内容完整，覆盖要点\n" +
               "- 建议增加更多细节";
    }

    private String generateSuggestion() {
        return "改进建议：\n" +
               "1. 增加具体案例说明\n" +
               "2. 深入分析技术原理\n" +
               "3. 准备更多开放性问题的回答";
    }

    private Integer generateFinalScore() {
        return new Random().nextInt(15) + 70;
    }

    private String generateReport(AiInterview interview) {
        return "【面试报告】\n\n" +
               "公司：" + interview.getTargetCompany() + "\n" +
               "职位：" + interview.getTargetPosition() + "\n" +
               "得分：" + interview.getScore() + "\n" +
               "\n【综合评价】\n" +
               "整体表现良好，建议继续加强技术基础和项目经验。\n" +
               "\n【改进方向】\n" +
               "1. 深入理解Java基础\n" +
               "2. 增加实战项目经验\n" +
               "3. 提高表达能力";
    }

    private String generateAiResponse(String message) {
        return "感谢您的提问！\n\n" +
               "这是一个很好的问题。根据您的需求，我为您提供以下建议：\n\n" +
               "1. 分析问题核心要点\n" +
               "2. 提供可行的解决方案\n" +
               "3. 给出具体的实施步骤\n\n" +
               "如果您需要更详细的解答，请随时告诉我！";
    }

}