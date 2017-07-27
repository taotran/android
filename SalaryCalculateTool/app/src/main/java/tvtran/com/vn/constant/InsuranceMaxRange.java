package tvtran.com.vn.constant;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/27/2017
 */
public class InsuranceMaxRange
{

  public static final int MIN_SAL                          = 1300000; // Muc luong co so
  private static final int AREA1_MIN_SAL                    = 3750000; // Muc luong toi thieu vung 1
  private static final int AREA2_MIN_SAL                    = 3320000;
  private static final int AREA3_MIN_SAL                    = 2900000;
  private static final int AREA4_MIN_SAL                    = 2580000;

  public static final int MAX_BHXH_BHYT_RANGE               = MIN_SAL * 20;
  public static final int MAX_BHTN_AREA1_RANGE              = AREA1_MIN_SAL * 20;
  public static final int MAX_BHTN_AREA2_RANGE              = AREA2_MIN_SAL * 20;
  public static final int MAX_BHTN_AREA3_RANGE              = AREA3_MIN_SAL * 20;
  public static final int MAX_BHTN_AREA4_RANGE              = AREA4_MIN_SAL * 20;

  public static final int DEFAULT_OVER_RANGE_BHXH           = 2080000;  //Old: 1936000;
  public static final int DEFAULT_OVER_RANGE_BHYT           = 390000; //Old: 363000;
  public static final int DEFAULT_OVER_RANGE_BHTN           = 750000;

  public static final int DEFAULT_EMPLOYER_OVER_RANGE_BHXH  = 4356000;
  public static final int DEFAULT_EMPLOYER_OVER_RANGE_BHYT  = 726000;
  public static final int DEFAULT_EMPLOYER_OVER_RANGE_BHTN  = 750000;
}
