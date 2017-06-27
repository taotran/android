package tvtran.com.vn.service;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/26/2017
 */
public class NetToGrossCalculator implements ICalculator
{
  //@formatter:off
  private static final double MIN_SAL                     = 1210000;

  private static final double MAX_SAL_FOR_BHXH_BHYT       = MIN_SAL * 20; // = 24.200.000 VND

  private static final double MAX_BHXH_BHXH               = 1936000; //for salary > MAX_SAL_FOR_BHXH_BHYT
  private static final double MAX_BHXH_BHYT               = 363000; //for salary > MAX_SAL_FOR_BHXH_BHYT
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

  private static final double GIAM_TRU_GIA_CANH_BAN_THAN  = 9000000;
  private static final double GIA_CANH_PHU_THUOC          = 3600000;

  //@formatter:on

  private Double inputNetSalary;
  private int numberOfDependencies;

  public NetToGrossCalculator(Double inputNetSalary, int numberOfDependencies)
  {
    this.inputNetSalary = inputNetSalary;
    this.numberOfDependencies = numberOfDependencies;
  }

  @Override
  public double calculate()
  {
    double salaryAfterDependenciesSubtraction = calcDependenciesSubtraction(numberOfDependencies, inputNetSalary);
    System.out.println("salaryAfterDependenciesSubtraction = " + salaryAfterDependenciesSubtraction);

    double appliedThueTNCNSalary = calcThueTNCNByRange(salaryAfterDependenciesSubtraction);
    System.out.println("appliedThueTNCNSalary = " + appliedThueTNCNSalary);

    double salaryBeforeTax = calcSalaryBeforeTax(appliedThueTNCNSalary, 0);

    double finalGrossSalary = calcFinalSalary(null, salaryBeforeTax);

    System.out.println(finalGrossSalary);

    return finalGrossSalary;
  }

  @Override
  public Double calcInsurancesSubtraction(Double salary)
  {
    return null;
  }

  @Override
  public Double calcDependenciesSubtraction(int numberOfDependencies, Double netSalary)
  {
    System.out.println("numberOfDependencies = [" + numberOfDependencies + "], netSalary = [" + netSalary + "]");
    return netSalary - GIAM_TRU_GIA_CANH_BAN_THAN - (numberOfDependencies * GIA_CANH_PHU_THUOC);
  }

  @Override
  public Double calcThueTNCNByRange(Double salaryAfterDependenciesSubtraction)
  {
    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_6) {
      return (salaryAfterDependenciesSubtraction - 9850000) / N2G_UNDER_TAX_CONST_7;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_5 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_6) {
      return (salaryAfterDependenciesSubtraction - 5850000) / N2G_UNDER_TAX_CONST_6;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_4 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_5) {
      return (salaryAfterDependenciesSubtraction - 3250000) / N2G_UNDER_TAX_CONST_5;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_3 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_4) {
      return (salaryAfterDependenciesSubtraction - 1650000) / N2G_UNDER_TAX_CONST_4;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_2 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_3) {
      return (salaryAfterDependenciesSubtraction - 750000) / N2G_UNDER_TAX_CONST_3;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_1 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_2) {
      return (salaryAfterDependenciesSubtraction - 250000) / N2G_UNDER_TAX_CONST_2;
    }

    if (salaryAfterDependenciesSubtraction < N2G_UNDER_TAX_RANGE_1) {
      return salaryAfterDependenciesSubtraction / N2G_UNDER_TAX_CONST_1;
    }
    return salaryAfterDependenciesSubtraction;
  }

  @Override
  public Double calcFinalSalary(Double salaryAfterInsurances /* NULL here */, Double salaryBeforeTax /* SAL before Tax*/)
  {
    return salaryBeforeTax < MAX_SAL_FOR_BHXH_BHYT ? (salaryBeforeTax) / 0.895 : (salaryBeforeTax + MAX_BHXH_BHXH + MAX_BHXH_BHYT) / (1 - 0.01);
  }

  @Override
  public double calcEmployerTotalPaid(Double salary)
  {
    return 0;
  }

  final Double calcSalaryBeforeTax(Double appliedThueTNCNSalary, int numberOfDependencies) {
    return appliedThueTNCNSalary + GIAM_TRU_GIA_CANH_BAN_THAN + (GIA_CANH_PHU_THUOC*numberOfDependencies);
  }


//  Double calcFinalSalary(Double appliedThueTNCNSalary, int numberOfDependencies) {
//
//    return thuNhapTruocThue < MAX_SAL_FOR_BHXH_BHYT ? (thuNhapTruocThue) / 0.895 : (thuNhapTruocThue + MAX_BHXH_BHXH + MAX_BHXH_BHYT) / (1 - 0.01);
//  }
}
