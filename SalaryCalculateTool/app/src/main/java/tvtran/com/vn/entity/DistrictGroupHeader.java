package tvtran.com.vn.entity;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/27/2017
 */
public class DistrictGroupHeader implements ExpandableGroupEntity
{
  private int id;
  private int cityId;
  private String displayValue;

  public DistrictGroupHeader()
  {
  }

  public DistrictGroupHeader(int id, int cityId, String displayValue)
  {
    this.id = id;
    this.cityId = cityId;
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

  public int getCityId()
  {
    return cityId;
  }
}
