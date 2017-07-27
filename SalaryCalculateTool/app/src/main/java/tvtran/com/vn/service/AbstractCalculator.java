package tvtran.com.vn.service;

import tvtran.com.vn.constant.InsuranceMaxRange;
import tvtran.com.vn.entity.Detail;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static tvtran.com.vn.utils.Utils.formattedDouble;
import static tvtran.com.vn.utils.Utils.writeContentToDetailList;

/**
 * This class provides GROSS > NET functions(not all) which are also used in NET > GROSS for output details information
 *
 * Property of CODIX Bulgaria EAD
 * Created by tvtran
 * Date:  6/28/2017
 */
public abstract class AbstractCalculator implements ICalculator
{

  //@formatter:off
  private static final double BHXH                        = 0.08;
  private static final double BHYT                        = 0.015;
  private static final double BHTN                        = 0.01;

  private static final double EMPLOYER_BHXH               = 0.175;
  private static final double EMPLOYER_BHYT               = 0.03;
  private static final double EMPLOYER_BHTN               = 0.01;

  private static final double EIGHTY_MILIONS_RANGE        = 80000000f;
  private static final double FIFTY_TWO_MILIONS_RANGE     = 52000000f;
  private static final double THIRTY_TWO_MILIONS_RANGE    = 32000000f;
  private static final double EIGHTEEN_MILIONS_RANGE      = 18000000f;
  private static final double TEN_MILIONS_RANGE           = 10000000f;
  private static final double FIVE_MILIONS_RANGE          = 5000000f;

  private static final double MAX_TNCN_UNDER_5_MIL        = 250000;
  private static final double MAX_TNCN_5_10_MIL           = 500000;
  private static final double MAX_TNCN_10_18_MIL          = 1200000;
  private static final double MAX_TNCN_18_32_MIL          = 2800000;
  private static final double MAX_TNCN_32_52_MIL          = 5000000;
  private static final double MAX_TNCN_52_80_MIL          = 8400000;

  protected Double inputSalary;
  protected int numberOfDependencies;

  protected List<Detail> detailList = new ArrayList<>();
  protected List<Detail> detailTNCNList = new ArrayList<>();
  protected List<Detail> employerDetailList = new ArrayList<>();

  public AbstractCalculator(Double inputSalary, int numberOfDependencies, Map<Integer, List<Detail>> detailsMap)
  {
    this.inputSalary = inputSalary;
    this.numberOfDependencies = numberOfDependencies;
    this.detailList = detailsMap.get(1);
    this.detailTNCNList = detailsMap.get(2);
    this.employerDetailList = detailsMap.get(3);
  }

  //@formatter:on
  @Override
  public Double calcInsurancesSubtraction(Double salary)
  {
    final double appliedBHXH = salary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE ? salary * BHXH : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHXH;
    final double appliedBHYT = salary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE ? salary * BHYT : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHYT;
    final double appliedBHTN = salary <= InsuranceMaxRange.MAX_BHTN_AREA1_RANGE ? salary * BHTN : InsuranceMaxRange.DEFAULT_OVER_RANGE_BHTN;

    writeContentToDetailList(detailList, 1, formattedDouble(appliedBHXH));
    writeContentToDetailList(detailList, 2, formattedDouble(appliedBHYT));
    writeContentToDetailList(detailList, 3, formattedDouble(appliedBHTN));
    return salary - (appliedBHXH + appliedBHYT + appliedBHTN);
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
      aboveEightyMilRangeTax = (salaryAfterDependenciesSubtraction - EIGHTY_MILIONS_RANGE) * 0.35;
      fiftyTwoToEightyMilRangeTax = MAX_TNCN_52_80_MIL;
      thirtyTwoToFiftyTwoMilRangeTax = MAX_TNCN_32_52_MIL;
      eighteenToThirtyTwoMilRangeTax = MAX_TNCN_18_32_MIL;
      tenToEighteenMilRangeTax = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax = MAX_TNCN_5_10_MIL;
      underFiveRangeTax = MAX_TNCN_UNDER_5_MIL;
    }

    if ((salaryAfterDependenciesSubtraction > FIFTY_TWO_MILIONS_RANGE) && (salaryAfterDependenciesSubtraction <= EIGHTY_MILIONS_RANGE && salaryAfterDependenciesSubtraction < EIGHTY_MILIONS_RANGE)) {
      fiftyTwoToEightyMilRangeTax = (salaryAfterDependenciesSubtraction - FIFTY_TWO_MILIONS_RANGE) * 0.30;
      thirtyTwoToFiftyTwoMilRangeTax = MAX_TNCN_32_52_MIL;
      eighteenToThirtyTwoMilRangeTax = MAX_TNCN_18_32_MIL;
      tenToEighteenMilRangeTax = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax = MAX_TNCN_5_10_MIL;
      underFiveRangeTax = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > THIRTY_TWO_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= FIFTY_TWO_MILIONS_RANGE) {
      thirtyTwoToFiftyTwoMilRangeTax = (salaryAfterDependenciesSubtraction - THIRTY_TWO_MILIONS_RANGE) * 0.25;
      eighteenToThirtyTwoMilRangeTax = MAX_TNCN_18_32_MIL;
      tenToEighteenMilRangeTax = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax = MAX_TNCN_5_10_MIL;
      underFiveRangeTax = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > EIGHTEEN_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= THIRTY_TWO_MILIONS_RANGE) {
      eighteenToThirtyTwoMilRangeTax = (salaryAfterDependenciesSubtraction - EIGHTEEN_MILIONS_RANGE) * 0.20;
      tenToEighteenMilRangeTax = MAX_TNCN_10_18_MIL;
      fiveToTenMilRangeTax = MAX_TNCN_5_10_MIL;
      underFiveRangeTax = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > TEN_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= EIGHTEEN_MILIONS_RANGE) {
      tenToEighteenMilRangeTax = (salaryAfterDependenciesSubtraction - TEN_MILIONS_RANGE) * 0.15;
      fiveToTenMilRangeTax = MAX_TNCN_5_10_MIL;
      underFiveRangeTax = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction > FIVE_MILIONS_RANGE && salaryAfterDependenciesSubtraction <= TEN_MILIONS_RANGE) {
      fiveToTenMilRangeTax = (salaryAfterDependenciesSubtraction - FIVE_MILIONS_RANGE) * 0.10;
      underFiveRangeTax = MAX_TNCN_UNDER_5_MIL;
    }

    if (salaryAfterDependenciesSubtraction < FIVE_MILIONS_RANGE && salaryAfterDependenciesSubtraction > 0) {
      underFiveRangeTax = salaryAfterDependenciesSubtraction * 0.05;
    }

    writeContentToDetailList(detailTNCNList, 0, "5%", formattedDouble(underFiveRangeTax));
    writeContentToDetailList(detailTNCNList, 1, "10%",formattedDouble(fiveToTenMilRangeTax));
    writeContentToDetailList(detailTNCNList, 2, "15%",formattedDouble(tenToEighteenMilRangeTax));
    writeContentToDetailList(detailTNCNList, 3, "20%",formattedDouble(eighteenToThirtyTwoMilRangeTax));
    writeContentToDetailList(detailTNCNList, 4, "25%",formattedDouble(thirtyTwoToFiftyTwoMilRangeTax));
    writeContentToDetailList(detailTNCNList, 5, "30%",formattedDouble(fiftyTwoToEightyMilRangeTax));
    writeContentToDetailList(detailTNCNList, 6, "35%",formattedDouble(aboveEightyMilRangeTax));


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
  public double calcEmployerTotalPaid(Double grossSalary)
  {
    final double appliedBHXH = grossSalary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE ? grossSalary * EMPLOYER_BHXH : InsuranceMaxRange.DEFAULT_EMPLOYER_OVER_RANGE_BHXH;
    final double appliedBHYT = grossSalary <= InsuranceMaxRange.MAX_BHXH_BHYT_RANGE ? grossSalary * EMPLOYER_BHYT : InsuranceMaxRange.DEFAULT_EMPLOYER_OVER_RANGE_BHYT;
    final double appliedBHTN = grossSalary <= InsuranceMaxRange.MAX_BHTN_AREA1_RANGE ? grossSalary * EMPLOYER_BHTN : InsuranceMaxRange.DEFAULT_EMPLOYER_OVER_RANGE_BHTN;
    final double total = grossSalary + (appliedBHXH + appliedBHYT + appliedBHTN);

    writeContentToDetailList(employerDetailList, 0, formattedDouble(grossSalary));
    writeContentToDetailList(employerDetailList, 1, formattedDouble(appliedBHXH));
    writeContentToDetailList(employerDetailList, 2, formattedDouble(appliedBHYT));
    writeContentToDetailList(employerDetailList, 3, formattedDouble(appliedBHTN));
    writeContentToDetailList(employerDetailList, 4, formattedDouble(total));

    return total;
  }

}
