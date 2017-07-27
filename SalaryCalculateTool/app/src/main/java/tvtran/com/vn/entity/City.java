package tvtran.com.vn.entity;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/27/2017
 */
public class City implements IdentifiableEntity
{
  private int id;
  private String name;

  public City(int id, String name)
  {
    this.id = id;
    this.name = name;
  }

  @Override
  public int getId()
  {
    return id;
  }

  @Override
  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
}
