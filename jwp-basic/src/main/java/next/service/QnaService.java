package next.service;

import core.exception.CannotDeleteException;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

import java.util.List;

public class QnaService {
  private static QnaService qnaService = new QnaService();

  private QuestionDao questionDao = QuestionDao.getInstance();
  private AnswerDao answerDao = AnswerDao.getInstance();

  private QnaService() {}

  public static QnaService getInstance() {
    return qnaService;
  }

  public void deleteQuestion(long questionId, User user) throws CannotDeleteException {
    Question question = questionDao.findById(questionId);

    if (question == null) {
      throw new CannotDeleteException("해당하는 게시물이 없습니다.");
    }

    if (!question.getUserId().equals(user.getUserId())) {
      throw new CannotDeleteException("소유주만 삭제할 수 있습니다.");
    }

    List<Answer> answers = answerDao.findAllByQuestionId(questionId);
    if (answers.isEmpty()) {
      questionDao.delete(questionId);
    }

    for (Answer answer : answers) {
      if (!answer.getUserId().equals(question.getUserId())) {
        throw new CannotDeleteException("다른 사용자가 추가한 댓글이 존재해 삭제할 수 없습니다.");
      }
    }

    questionDao.delete(questionId);
  }
}
