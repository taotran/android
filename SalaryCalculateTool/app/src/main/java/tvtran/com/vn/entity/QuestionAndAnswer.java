package tvtran.com.vn.entity;

import java.io.Serializable;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/29/2017
 */
public class QuestionAndAnswer implements Serializable
{
  private int id;
  private String shortQuestion;
  private String question;
  private String answer;

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getShortQuestion()
  {
    return shortQuestion;
  }

  public void setShortQuestion(String shortQuestion)
  {
    this.shortQuestion = shortQuestion;
  }

  public String getQuestion()
  {
    return question;
  }

  public void setQuestion(String question)
  {
    this.question = question;
  }

  public String getAnswer()
  {
    return answer;
  }

  public void setAnswer(String answer)
  {
    this.answer = answer;
  }
}
