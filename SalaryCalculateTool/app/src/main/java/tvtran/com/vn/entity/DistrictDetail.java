package tvtran.com.vn.entity;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  7/28/2017
 */
public class DistrictDetail implements IdentifiableEntity
{

  public DistrictDetail() {

  }

  public DistrictDetail(int districtId, String address, String contactInfo, String others)
  {
    this.districtId = districtId;
    this.address = address;
    this.contactInfo = contactInfo;
    this.others = others;
  }

  public DistrictDetail(int districtId, String address, String contactInfo, String others, String bankName, String bankAccount)
  {
    this(districtId, address, contactInfo, others);
    this.bankName = bankName;
    this.bankAccount = bankAccount;
  }

  private int districtId;
  private String address;
  private String contactInfo;
  private String others;

  private String bankName;
  private String bankAccount;

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public String getContactInfo()
  {
    return contactInfo;
  }

  public void setContactInfo(String contactInfo)
  {
    this.contactInfo = contactInfo;
  }

  public String getOthers()
  {
    return others;
  }

  public void setOthers(String others)
  {
    this.others = others;
  }

  public String getBankName()
  {
    return bankName;
  }

  public void setBankName(String bankName)
  {
    this.bankName = bankName;
  }

  public String getBankAccount()
  {
    return bankAccount;
  }

  public void setBankAccount(String bankAccount)
  {
    this.bankAccount = bankAccount;
  }

  @Override
  public int getId()
  {
    return districtId;
  }

  @Override
  public void setId(int id)
  {
    this.districtId = id;
  }
}
