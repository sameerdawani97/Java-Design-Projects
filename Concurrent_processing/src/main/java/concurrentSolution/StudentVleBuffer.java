package concurrentSolution;

import java.util.Objects;

/**
 * A class represents a buffer for input data from the studentVle.csv file.
 */
public class StudentVleBuffer {

  private String codeModule;
  private String codePresentation;
  private String date;
  private Integer sumClick;

  /**
   * Constructor for StudentVleBuffer
   * @param codeModule - A module is equivalent to a course code, in Northeastern terms.
   * @param codePresentation - A presentation is equivalent to an offering of a course.
   * @param date - A specific day within the course, relative to the course start date.
   * @param sumClick - The number of times the given student clicked on the given resource on the
   * given day.
   */
  public StudentVleBuffer(String codeModule, String codePresentation, String date,
      Integer sumClick) {
    this.codeModule = codeModule;
    this.codePresentation = codePresentation;
    this.date = date;
    this.sumClick = sumClick;
  }

  /**
   * Returns the code module that is equivalent to a course code, in Northeastern terms.
   * @return the code module
   */
  public String getCodeModule() {
    return this.codeModule;
  }

  /**
   * Returns the code presentation that is equivalent to an offering of a course.
   * @return the code presentation
   */
  public String getCodePresentation() {
    return this.codePresentation;
  }

  /**
   * Returns the specific day within the course, relative to the course start date.
   * @return the date
   */
  public String getDate() {
    return this.date;
  }

  /**
   * Returns the number of times the given student clicked on the given resource on the
   * given day.
   * @return the sumClick
   */
  public Integer getSumClick() {
    return this.sumClick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentVleBuffer that = (StudentVleBuffer) o;
    return Objects.equals(codeModule, that.codeModule) && Objects.equals(
        codePresentation, that.codePresentation) && Objects.equals(date, that.date)
        && Objects.equals(sumClick, that.sumClick);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codeModule, codePresentation, date, sumClick);
  }

  @Override
  public String toString() {
    return "StudentVleBuffer{" +
        "codeModule='" + codeModule + '\'' +
        ", codePresentation='" + codePresentation + '\'' +
        ", date='" + date + '\'' +
        ", sumClick=" + sumClick +
        '}';
  }
}
