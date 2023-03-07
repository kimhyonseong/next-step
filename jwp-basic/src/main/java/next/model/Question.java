package next.model;

import java.util.Date;

public class Question {
  private long questionId;
  private String writer;
  private String title;
  private String contents;
  private Date createdDate;
  private int countOfAnswer;

  public Question(String writer, String title, String contents) {
    this(0,writer,title,contents,new Date(),0);
  }

  public Question(long questionId,String writer, String title, String contents, Date createdDate, int countOfAnswer) {
    this.questionId = questionId;
    this.writer = writer;
    this.title = title;
    this.contents = contents;
    this.createdDate = createdDate;
    this.countOfAnswer = countOfAnswer;
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getCountOfAnswer() {
    return countOfAnswer;
  }

  public void setCountOfAnswer(int countOfAnswer) {
    this.countOfAnswer = countOfAnswer;
  }

  @Override
  public String toString() {
    return "Question{" +
            "questionId=" + questionId +
            ", writer='" + writer + '\'' +
            ", title='" + title + '\'' +
            ", contents='" + contents + '\'' +
            ", createdDate=" + createdDate +
            ", countOfAnswer=" + countOfAnswer +
            '}';
  }
}
