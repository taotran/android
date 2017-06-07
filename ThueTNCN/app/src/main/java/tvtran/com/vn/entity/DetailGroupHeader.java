package tvtran.com.vn.entity;

import tvtran.com.vn.adapter.ExpandableGroupEntity;

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
}
