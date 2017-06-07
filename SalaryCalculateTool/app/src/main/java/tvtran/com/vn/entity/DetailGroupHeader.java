package tvtran.com.vn.entity;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/5/2017
 */
public class DetailGroupHeader implements ExpandableGroupEntity
{
  private int id;
  private String displayValue;

  public DetailGroupHeader(int id, String displayValue)
  {
    this.id = id;
    this.displayValue = displayValue;
  }

  public DetailGroupHeader(String displayValue)
  {
    this.displayValue = displayValue;
  }

  @Override
  public Integer getId()
  {
    return id;
  }

  @Override
  public String getDisplayValue()
  {
    return displayValue;
  }

  @Override
  public int compareTo(ExpandableGroupEntity o)
  {
    return 0;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DetailGroupHeader that = (DetailGroupHeader) o;

    if (id != that.id) {
      return false;
    }
    return displayValue.equals(that.displayValue);
  }

  @Override
  public int hashCode()
  {
    int result = id;
    result = 31 * result + displayValue.hashCode();
    return result;
  }
}
