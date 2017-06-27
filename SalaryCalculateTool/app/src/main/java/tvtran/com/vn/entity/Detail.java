package tvtran.com.vn.entity;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/2/2017
 */
public class Detail implements IdentifiableEntity
{
  private String key;
  private String value;
  private String value2;
  private String type;

  private int index;

  public Detail()
  {
  }

  public Detail(String key, int index)
  {
    this.key = key;
    this.index = index;
  }

  public Detail(String key, String value, String type, int index)
  {
    this.key = key;
    this.value = value;
    this.type = type;
    this.index = index;
  }

  public Detail(String key, String value, String value2, String type, int index)
  {
    this.key = key;
    this.value = value;
    this.value2 = value2;
    this.type = type;
    this.index = index;
  }

  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getValue2()
  {
    return value2;
  }

  public void setValue2(String value2)
  {
    this.value2 = value2;
  }

  @Override
  public int getId()
  {
    return index;
  }

  @Override
  public void setId(int id)
  {
    this.index = id;
  }

  @Override
  public boolean equals(Object obj)
  {
    return this.getKey().equals(((Detail) obj).getKey());
  }
}
