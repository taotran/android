package tvtran.com.vn.service;

import tvtran.com.vn.constant.InsuranceMaxRange;
import tvtran.com.vn.entity.ConfigObject;
import tvtran.com.vn.entity.Detail;

import java.util.List;
import java.util.Map;

import static tvtran.com.vn.utils.Utils.formattedDouble;
import static tvtran.com.vn.utils.Utils.writeContentToDetailList;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/26/2017
 */
public class NetToGrossCalculator extends AbstractCalculator
{
  //@formatter:off
  private static final double MAX_SAL_FOR_BHXH_BHYT       = InsuranceMaxRange.MIN_SAL * 20; // Before = 24.200.000 VND(OLD) => 26.000.000

//  private static final double MAX_BHXH_BHXH               = 1936000; //for salary > MAX_SAL_FOR_BHXH_BHYT
//  private static final double MAX_BHXH_BHYT               = 363000; //for salary > MAX_SAL_FOR_BHXH_BHYT
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

  public NetToGrossCalculator(Double inputNetSalaryVND, Double inputNetSalaryUSD, int numberOfDependencies, Map<Integer, List<Detail>> detailsMap, ConfigObject configObject)
  {
    super(inputNetSalaryVND, inputNetSalaryUSD, numberOfDependencies, detailsMap, configObject);
  }

  @Override
  public double calculate()
  {
    double salaryAfterDependenciesSubtraction = calcDependenciesSubtraction(numberOfDependencies, totalSalary);

    double appliedThueTNCNSalary = calcThueTNCNByRange(salaryAfterDependenciesSubtraction);

    // call this to append Thue TNCN to details only
    super.calcThueTNCNByRange(appliedThueTNCNSalary);

    writeContentToDetailList(detailList, 7, formattedDouble(appliedThueTNCNSalary));

    writeContentToDetailList(detailList, 8, formattedDouble(appliedThueTNCNSalary - salaryAfterDependenciesSubtraction));

    double salaryBeforeTax = calcSalaryBeforeTax(appliedThueTNCNSalary, numberOfDependencies);

    double finalGrossSalary = calcFinalSalary(null, salaryBeforeTax);

    System.out.println(finalGrossSalary);


    // Luong GROSS
    writeContentToDetailList(detailList, 0, formattedDouble(finalGrossSalary));
    // Luong NET
    writeContentToDetailList(detailList, 9, formattedDouble(totalSalary));

    // just to output to screen
    calcInsurancesSubtraction(finalGrossSalary);

    // just to output employer details to screen
    calcEmployerTotalPaid(finalGrossSalary);

    return finalGrossSalary;
  }

  @Override
  public Double calcDependenciesSubtraction(int numberOfDependencies, Double netSalary)
  {
    System.out.println("numberOfDependencies = [" + numberOfDependencies + "], netSalary = [" + netSalary + "]");
    final double dependenciesDeduction = numberOfDependencies * GIA_CANH_PHU_THUOC;
    final double result = netSalary - GIAM_TRU_GIA_CANH_BAN_THAN - dependenciesDeduction;
    // giam tru ban than (5)
    writeContentToDetailList(detailList, 5, formattedDouble(GIAM_TRU_GIA_CANH_BAN_THAN));

    // giam tru phu thuoc (6)
    writeContentToDetailList(detailList, 6, formattedDouble(dependenciesDeduction));

    // Thu nhap chiu thue (7)
    writeContentToDetailList(detailList, 7, formattedDouble(result));
    return result;
  }

  @Override
  public Double calcThueTNCNByRange(Double salaryAfterDependenciesSubtraction)
  {
    double taxedSalary = salaryAfterDependenciesSubtraction;

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_6) {
      taxedSalary = (salaryAfterDependenciesSubtraction - 9850000) / N2G_UNDER_TAX_CONST_7;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_5 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_6) {
      taxedSalary = (salaryAfterDependenciesSubtraction - 5850000) / N2G_UNDER_TAX_CONST_6;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_4 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_5) {
      taxedSalary = (salaryAfterDependenciesSubtraction - 3250000) / N2G_UNDER_TAX_CONST_5;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_3 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_4) {
      taxedSalary = (salaryAfterDependenciesSubtraction - 1650000) / N2G_UNDER_TAX_CONST_4;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_2 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_3) {
      taxedSalary = (salaryAfterDependenciesSubtraction - 750000) / N2G_UNDER_TAX_CONST_3;
    }

    if (salaryAfterDependenciesSubtraction > N2G_UNDER_TAX_RANGE_1 && salaryAfterDependenciesSubtraction <= N2G_UNDER_TAX_RANGE_2) {
      taxedSalary = (salaryAfterDependenciesSubtraction - 250000) / N2G_UNDER_TAX_CONST_2;
    }

    if (salaryAfterDependenciesSubtraction < N2G_UNDER_TAX_RANGE_1) {
      taxedSalary = salaryAfterDependenciesSubtraction / N2G_UNDER_TAX_CONST_1;
    }


    return taxedSalary;
  }

  @Override
  public Double calcFinalSalary(Double salaryAfterInsurances /* NULL here */, Double salaryBeforeTax /* SAL before Tax*/)
  {
//    return salaryBeforeTax < MAX_SAL_FOR_BHXH_BHYT ? (salaryBeforeTax) / 0.895 : (salaryBeforeTax + InsuranceMaxRange.DEFAULT_OVER_RANGE_BHXH + InsuranceMaxRange.DEFAULT_OVER_RANGE_BHYT) / (1 - 0.01);
    return salaryBeforeTax < MAX_SAL_FOR_BHXH_BHYT ? (salaryBeforeTax) / 0.895 : salaryBeforeTax + InsuranceMaxRange.DEFAULT_OVER_RANGE_BHXH + InsuranceMaxRange.DEFAULT_OVER_RANGE_BHYT + InsuranceMaxRange.DEFAULT_OVER_RANGE_BHTN;
  }

  final Double calcSalaryBeforeTax(Double appliedThueTNCNSalary, int numberOfDependencies)
  {
    final double result = appliedThueTNCNSalary + GIAM_TRU_GIA_CANH_BAN_THAN + (GIA_CANH_PHU_THUOC * numberOfDependencies);

    // TN truoc thue
    writeContentToDetailList(detailList, 4, formattedDouble(result));

    return result;
  }
}
