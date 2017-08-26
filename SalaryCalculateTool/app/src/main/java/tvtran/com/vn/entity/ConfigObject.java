package tvtran.com.vn.entity;

import java.io.Serializable;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  8/22/2017
 */
public class ConfigObject implements Serializable
{
  private double currRate = 22750;
  private double baseSalary = 1300000;
  private double rateBHXH = 8.0;
  private double rateBHYT = 1.5;
  private double rateBHTN = 1.0;

  public ConfigObject()
  {
  }

  public ConfigObject(double currRate, double baseSalary, double rateBHXH, double rateBHYT, double rateBHTN)
  {
    this.currRate = currRate;
    this.baseSalary = baseSalary;
    this.rateBHXH = rateBHXH;
    this.rateBHYT = rateBHYT;
    this.rateBHTN = rateBHTN;
  }

  public double getCurrRate()
  {
    return currRate;
  }

  public void setCurrRate(double currRate)
  {
    this.currRate = currRate;
  }

  public double getBaseSalary()
  {
    return baseSalary;
  }

  public void setBaseSalary(double baseSalary)
  {
    this.baseSalary = baseSalary;
  }

  public double getRateBHXH()
  {
    return rateBHXH;
  }

  public void setRateBHXH(double rateBHXH)
  {
    this.rateBHXH = rateBHXH;
  }

  public double getRateBHYT()
  {
    return rateBHYT;
  }

  public void setRateBHYT(double rateBHYT)
  {
    this.rateBHYT = rateBHYT;
  }

  public double getRateBHTN()
  {
    return rateBHTN;
  }

  public void setRateBHTN(double rateBHTN)
  {
    this.rateBHTN = rateBHTN;
  }


}
