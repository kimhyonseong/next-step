package next.model;

import java.util.Date;

public class Answer {
  private long answerId;
  private String writer;
  private String userId;
  private String contents;
  private Date createdDate;
  private long questionId;

  public Answer(String writer,String userId,String contents,long questionId) {
    this(0,writer,userId,contents,new Date(),questionId);
  }

  public Answer(long answerId,String writer,String userId,String contents,Date createdDate,long questionId) {
    this.answerId = answerId;
    this.userId = userId;
    this.writer = writer;
    this.contents = contents;
    this.createdDate = createdDate;
    this.questionId = questionId;
  }

  public long getAnswerId() {
    return answerId;
  }

  public void setAnswerId(long answerId) {
    this.answerId = answerId;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public long getTimeFromCreateDate() {
    return this.createdDate.getTime();
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }

  @Override
  public String toString() {
    return "Answer{" +
            "answerId=" + answerId +
            ", writer='" + writer + '\'' +
            ", userId='" + userId + '\'' +
            ", contents='" + contents + '\'' +
            ", createdDate=" + createdDate +
            ", questionId=" + questionId +
            '}';
  }
}
