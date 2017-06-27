package tvtran.com.vn.service;

import tvtran.com.vn.constant.InsuranceMaxRange;
import tvtran.com.vn.entity.Detail;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static tvtran.com.vn.utils.Utils.*;

/**
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/26/2017
 */
public class GrossToNetCalculator implements ICalculator
{

  //@formatter:off
  private static final double BHXH                        = 0.08;
  private static final double BHYT                        = 0.015;
  private static final double BHTN                        = 0.01;

  private static final double EMPLOYER_BHXH               = 0.18;
  private static final double EMPLOYER_BHYT               = 0.03;
  private static final double EMPLOYER_BHTN               = 0.01;

  private static final double EIGHTY_MILIONS_RANGE        = 80000000f;
  private static final double FIFTY_TWO_MILIONS_RANGE     = 52000000f;
  private static final double THIRTY_TWO_MILIONS_RANGE    = 32000000f;
  private static final double EIGHTEEN_MILIONS_RANGE      = 18000000f;
  private static final double TEN_MILIONS_RANGE           = 10000000f;
  private static final double FIVE_MILIONS_RANGE          = 5000000f;
  private static final double GIAM_TRU_GIA_CANH_BAN_THAN  = 9000000f;
  private static final double GIA_CANH_PHU_THUOC          = 3600000f;


  private static final double MAX_TNCN_UNDER_5_MIL        = 250000;
  private static final double MAX_TNCN_5_10_MIL           = 500000;
  private static final double MAX_TNCN_10_18_MIL          = 1200000;
  private static final double MAX_TNCN_18_32_MIL          = 2800000;
  private static final double MAX_TNCN_32_52_MIL          = 5000000;
  private static final double MAX_TNCN_52_80_MIL          = 8400000;

  //@formatter:on

  private Integer numberOfDependencies;

  private Double inputSalary;


  private List<Detail> detailList = new ArrayList<>();
  private List<Detail> detailTNCNList = new ArrayList<>();
  private List<Detail> employerDetailList = new ArrayList<>();

  public GrossToNetCalculator(Double inputSalary, Integer numberOfDependencies, Map<Integer, List<Detail>> detailsMap)
  {
    this.numberOfDependencies = numberOfDependencies;
    this.inputSalary = inputSalary;
    this.detailList = detailsMap.get(1);
    this.detailTNCNList = detailsMap.get(2);
    this.employerDetailList = detailsMap.get(3);
  }

  public double calculate()
  {
    writeContentToDetailList(detailList, 0, formattedDouble(inputSalary));
    double salaryAfterInsurancesSubtraction = calcInsurancesSubtraction(inputSalary);

    //Thu nhap truoc thue
    writeContentToDetailList(detailList, 4, formattedDouble(salaryAfterInsurancesSubtraction));

    double salaryAfterDependenciesSubtraction = calcDependenciesSubtraction(numberOfDependencies, salaryAfterInsurancesSubtraction);
    double thueTNCNByRange = calcThueTNCNByRange(salaryAfterDependenciesSubtraction);
    double finalSalary = calcFinalSalary(salaryAfterInsurancesSubtraction, thueTNCNByRange);
    System.out.println(finalSalary);
    writeContentToDetailList(detailList, 9, formattedDouble(finalSalary));

    //call to output EmployerDetails only
    calcEmployerTotalPaid(inputSalary);

    return finalSalary;
  }

  public Double calcInsurancesSubtraction(Double salary)
  {
    final double appliedBHXH = salary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE  ? salary * BHXH : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHXH;
    final double appliedBHYT = salary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE  ? salary * BHYT : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHYT;
    final double appliedBHTN = salary <= InsuranceMaxRange.MAX_BHTN_AREA1_RANGE ? salary * BHTN : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHTN;

    writeContentToDetailList(detailList, 1, formattedDouble(appliedBHXH));
    writeContentToDetailList(detailList, 2, formattedDouble(appliedBHYT));
    writeContentToDetailList(detailList, 3, formattedDouble(appliedBHTN));
    return salary - (appliedBHXH + appliedBHYT + appliedBHTN);
  }

  @Override
  public Double calcDependenciesSubtraction(int numberOfDependencies, Double salaryAfterInsurancesSubtraction)
  {
    final double dependenciesDeductedAmount = (numberOfDependencies * GIA_CANH_PHU_THUOC);
    final double result = salaryAfterInsurancesSubtraction - GIAM_TRU_GIA_CANH_BAN_THAN - dependenciesDeductedAmount;

    //giam tru gia canh ban than
    writeContentToDetailList(detailList, 5, formattedDouble(GIAM_TRU_GIA_CANH_BAN_THAN));
    //giam tru gia canh nguoi phu thuoc
    writeContentToDetailList(detailList, 6, formattedDouble(dependenciesDeductedAmount));
    //thu nhap chiu thue
    writeContentToDetailList(detailList, 7, formattedDouble(result));

    return result;
  }

  /*
    ----- (*) Chi tiết thuế thu nhập cá nhân (VND)
    ----- Mức chịu thuế	Thuế suất	Tiền nộp
    ----- Đến   5 triệu VND	                   5%	250,000
    ----- Trên  5 triệu VND đến 10 triệu VND	10%	500,000
    ----- Trên 10 triệu VND đến 18 triệu VND	15%	458,250
    ----- Trên 18 triệu VND đến 32 triệu VND	20%	0
    ----- Trên 32 triệu VND đến 52 triệu VND	25%	0
    ----- Trên 52 triệu VND đến 80 triệu VND	30%	0
  */
  @Override
  public Double calcThueTNCNByRange(Double salaryAfterDependenciesSubtraction)
  {
    double taxTotal;
    double aboveEightyMilRangeTax = 0;
    double fiftyTwoToEightyMilRangeTax = 0;
    double thirtyTwoToFiftyTwoMilRangeTax = 0;
    double eighteenToThirtyTwoMilRangeTax = 0;
    double tenToEighteenMilRangeTax = 0;
    double fiveToTenMilRangeTax = 0;
    double underFiveRangeTax = 0;

    if (salaryAfterDependenciesSubtraction > EIGHTY_MILIONS_RANGE) {
      aboveEightyMilRangeTax          = (salaryAfterDependenciesSubtraction - EIGHTY_MILIONS_RANGE) * 0.35;
      fiftyTwoToEightyMilRangeTax     = MAX_TNCN_52_80_MIL;
      thirtyTwoToFiftyTwoMilRangeTax  = MAX_TNCN_32_52_MIL;
      eighteenToThirtyTwoMilRangeTax  = MAX_TNCN_18_32_MIL;
      tenToEighteenMilRangeTax        = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax            = MAX_TNCN_5_10_MIL;
      underFiveRangeTax               = MAX_TNCN_UNDER_5_MIL;
    }

    if ((salaryAfterDependenciesSubtraction >= EIGHTY_MILIONS_RANGE) || (salaryAfterDependenciesSubtraction > FIFTY_TWO_MILIONS_RANGE && salaryAfterDependenciesSubtraction < EIGHTY_MILIONS_RANGE)) {
      fiftyTwoToEightyMilRangeTax   = (salaryAfterDependenciesSubtraction - FIFTY_TWO_MILIONS_RANGE) * 0.30;
      thirtyTwoToFiftyTwoMilRangeTax  = MAX_TNCN_32_52_MIL;
      eighteenToThirtyTwoMilRangeTax  = MAX_TNCN_18_32_MIL;
      tenToEighteenMilRangeTax        = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax            = MAX_TNCN_5_10_MIL;
      underFiveRangeTax               = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > THIRTY_TWO_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= FIFTY_TWO_MILIONS_RANGE) {
      thirtyTwoToFiftyTwoMilRangeTax  = (salaryAfterDependenciesSubtraction - THIRTY_TWO_MILIONS_RANGE) * 0.25;
      eighteenToThirtyTwoMilRangeTax  = MAX_TNCN_18_32_MIL;
      tenToEighteenMilRangeTax        = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax            = MAX_TNCN_5_10_MIL;
      underFiveRangeTax               = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > EIGHTEEN_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= THIRTY_TWO_MILIONS_RANGE) {
      eighteenToThirtyTwoMilRangeTax   = (salaryAfterDependenciesSubtraction - EIGHTEEN_MILIONS_RANGE) * 0.20;
      tenToEighteenMilRangeTax        = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax            = MAX_TNCN_5_10_MIL;
      underFiveRangeTax               = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > TEN_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= EIGHTEEN_MILIONS_RANGE) {
      tenToEighteenMilRangeTax        = (salaryAfterDependenciesSubtraction - TEN_MILIONS_RANGE) * 0.15;
      fiveToTenMilRangeTax            = MAX_TNCN_5_10_MIL;
      underFiveRangeTax               = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > FIVE_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= TEN_MILIONS_RANGE) {
      fiveToTenMilRangeTax            = (salaryAfterDependenciesSubtraction - FIVE_MILIONS_RANGE) * 0.10;
      underFiveRangeTax               = MAX_TNCN_UNDER_5_MIL;
    }

    writeContentToDetailList(detailTNCNList, 0, formattedDouble(underFiveRangeTax));
    writeContentToDetailList(detailTNCNList, 1, formattedDouble(fiveToTenMilRangeTax));
    writeContentToDetailList(detailTNCNList, 2, formattedDouble(tenToEighteenMilRangeTax));
    writeContentToDetailList(detailTNCNList, 3, formattedDouble(eighteenToThirtyTwoMilRangeTax));
    writeContentToDetailList(detailTNCNList, 4, formattedDouble(thirtyTwoToFiftyTwoMilRangeTax));
    writeContentToDetailList(detailTNCNList, 5, formattedDouble(fiftyTwoToEightyMilRangeTax));
    writeContentToDetailList(detailTNCNList, 6, formattedDouble(aboveEightyMilRangeTax));


    taxTotal = aboveEightyMilRangeTax
              + fiftyTwoToEightyMilRangeTax
              + thirtyTwoToFiftyTwoMilRangeTax
              + eighteenToThirtyTwoMilRangeTax
              + tenToEighteenMilRangeTax
              + fiveToTenMilRangeTax
              + underFiveRangeTax;
    //thue tncn
    writeContentToDetailList(detailList, 8, formattedDouble(taxTotal));

    return taxTotal;
  }

  @Override
  public Double calcFinalSalary(Double salaryAfterInsurances, Double appliedThueTNCNSalary)
  {
    return salaryAfterInsurances - appliedThueTNCNSalary;
  }

  @Override
  public double calcEmployerTotalPaid(Double grossSalary)
  {
    final double appliedBHXH = grossSalary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE  ? grossSalary * EMPLOYER_BHXH : InsuranceMaxRange.DEFAULT_EMPLOYER_OVER_RANGE_BHXH;
    final double appliedBHYT = grossSalary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE  ? grossSalary * EMPLOYER_BHYT : InsuranceMaxRange.DEFAULT_EMPLOYER_OVER_RANGE_BHYT;
    final double appliedBHTN = grossSalary <= InsuranceMaxRange.MAX_BHTN_AREA1_RANGE ? grossSalary * EMPLOYER_BHTN : InsuranceMaxRange.DEFAULT_EMPLOYER_OVER_RANGE_BHTN;
    final double total = grossSalary + (appliedBHXH + appliedBHYT + appliedBHTN);

    writeContentToDetailList(employerDetailList, 0, formattedDouble(grossSalary));
    writeContentToDetailList(employerDetailList, 1, formattedDouble(appliedBHXH));
    writeContentToDetailList(employerDetailList, 2, formattedDouble(appliedBHYT));
    writeContentToDetailList(employerDetailList, 3, formattedDouble(appliedBHTN));
    writeContentToDetailList(employerDetailList, 4, formattedDouble(total));

    return total;
  }

  private String formattedDouble(double number) {
    if (number < 1000) {
      return String.valueOf(number);
    }
    try {
      NumberFormat formatter = new DecimalFormat("###,###");
      String resp = formatter.format(number);
//      resp = resp.replaceAll(",", ".");
      return resp;
    } catch (Exception e) {
      return "";
    }
  }
}