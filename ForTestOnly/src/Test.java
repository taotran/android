/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  5/26/2017
 */
public class Test
{
  private static final double BHXH = 0.08;
  private static final double BHYT = 0.015;
  private static final double BHTN = 0.01;
  //@formatter:off

  private static final double MIN_SAL = 1210000;

  private static final double MAX_SAL_FOR_BHXH_BHYT = MIN_SAL * 20; // = 24.200.000 VND

  private static final double MAX_BHXH_BHXH = 1936000; //for salary > MAX_SAL_FOR_BHXH_BHYT
  private static final double MAX_BHXH_BHYT = 363000; //for salary > MAX_SAL_FOR_BHXH_BHYT

  private static final double EIGHTY_MILIONS_RANGE        = 80000000f;
  private static final double FIFTY_TWO_MILIONS_RANGE     = 52000000f;
  private static final double THIRTY_TWO_MILIONS_RANGE    = 32000000f;
  private static final double EIGHTEEN_MILIONS_RANGE      = 18000000f;
  private static final double TEN_MILIONS_RANGE           = 10000000f;
  private static final double FIVE_MILIONS_RANGE          = 5000000f;
  private static final double GIAM_TRU_GIA_CANH_BAN_THAN  = 9000000;
  private static final double GIA_CANH_PHU_THUOC          = 3600000;

  private static final double N2G_UNDER_TAX_RANGE_1       = 4750000;
  private static final double N2G_UNDER_TAX_RANGE_2       = 9250000;
  private static final double N2G_UNDER_TAX_RANGE_3       = 16050000;
  private static final double N2G_UNDER_TAX_RANGE_4       = 27250000;
  private static final double N2G_UNDER_TAX_RANGE_5       = 42250000;
  private static final double N2G_UNDER_TAX_RANGE_6       = 61850000;

  private static final double N2G_UNDER_TAX_CONST_1       = 0.95;
  private static final double N2G_UNDER_TAX_CONST_2       = 0.9;
  private static final double N2G_UNDER_TAX_CONST_3       = 0.85;
  private static final double N2G_UNDER_TAX_CONST_4       = 0.8;
  private static final double N2G_UNDER_TAX_CONST_5       = 0.75;
  private static final double N2G_UNDER_TAX_CONST_6       = 0.70;
  private static final double N2G_UNDER_TAX_CONST_7       = 0.65;

  public static void main(String[] args) throws Exception {
//    String date = "10/08/2015";
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    System.out.println(sdf.parse(date).getTime());
//    System.out.println();

    System.out.println(calcTNCT_N2G(24600000, 2));

    System.out.println(calcGROSS_N2G(calcTNCT_N2G(24600000, 2), 2));
  }

  private static double calcTNCT_N2G(double netSalary, int numberOfDependence)
  {
    double tnctAfterGiamTru = netSalary - GIAM_TRU_GIA_CANH_BAN_THAN - (numberOfDependence * GIA_CANH_PHU_THUOC);
    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_6) {
      return (tnctAfterGiamTru - 9850000) / N2G_UNDER_TAX_CONST_7;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_5 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_6) {
      return (tnctAfterGiamTru - 5850000) / N2G_UNDER_TAX_CONST_6;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_4 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_5) {
      return (tnctAfterGiamTru - 3250000) / N2G_UNDER_TAX_CONST_5;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_3 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_4) {
      return (tnctAfterGiamTru - 1650000) / N2G_UNDER_TAX_CONST_4;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_2 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_3) {
      return (tnctAfterGiamTru - 750000) / N2G_UNDER_TAX_CONST_3;
    }

    if (tnctAfterGiamTru > N2G_UNDER_TAX_RANGE_1 && tnctAfterGiamTru <= N2G_UNDER_TAX_RANGE_2) {
      return (tnctAfterGiamTru - 250000) / N2G_UNDER_TAX_CONST_2;
    }

    if (tnctAfterGiamTru < N2G_UNDER_TAX_RANGE_1) {
      return tnctAfterGiamTru / N2G_UNDER_TAX_CONST_1;
    }
    return tnctAfterGiamTru;
  }

  private static double calcGROSS_N2G(double tnct, int numberOfDependence) {
    final double thuNhapTruocThue = tnct + GIAM_TRU_GIA_CANH_BAN_THAN + (GIA_CANH_PHU_THUOC*numberOfDependence);
    return thuNhapTruocThue < MAX_SAL_FOR_BHXH_BHYT ? (thuNhapTruocThue) / 0.895 : (thuNhapTruocThue + MAX_BHXH_BHXH + MAX_BHXH_BHYT) / (1-0.01);
  }


}
